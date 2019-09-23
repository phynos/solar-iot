package com.phynos.framework.simulator.raw.codec;

import com.phynos.framework.simulator.raw.message.AbstractMessage;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class SolarProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    public SolarProtocolCodecFactory() {
    	super.addMessageEncoder(AbstractMessage.class, SolarMessageEncoder.class);
        super.addMessageDecoder(SolarMessageDecoder.class);
    }
}
