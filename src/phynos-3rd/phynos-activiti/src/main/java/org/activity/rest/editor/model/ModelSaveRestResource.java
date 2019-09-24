/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activity.rest.editor.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Tijs Rademakers
 */
@RestController
@RequestMapping("/api/activiti")
public class ModelSaveRestResource implements ModelDataJsonConstants {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private RepositoryService repositoryService;
  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
  @ResponseStatus(value = HttpStatus.OK)
  public void saveModel(@PathVariable String modelId,
                        @RequestParam("name") String name,
                        @RequestParam("json_xml") String json_xml,
                        @RequestParam("svg_xml") String svg_xml,
                        @RequestParam("description") String description) {
    try {
      Model model = repositoryService.getModel(modelId);
      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
      modelJson.put(MODEL_NAME, name);
      modelJson.put(MODEL_DESCRIPTION, description);
      model.setMetaInfo(modelJson.toString());
      model.setName(name);
      repositoryService.saveModel(model);
      repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));
      InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
      TranscoderInput input = new TranscoderInput(svgStream);
      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);
      // Do the transformation
      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();
    } catch (Exception e) {
      logger.error("Error saving model", e);
      throw new ActivitiException("Error saving model", e);
    }
  }
}
