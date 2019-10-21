## 工作流引擎
abc

## 工作流的相关概念
1. 流程定义：（ProcessDefinition）：  
    流程的定义：流程图。表示一个流程的信息；一个流程定义（如：请假流程图）
2. 流程实例：（ProcessInstance）：
    启动一个流程：称为创建一个流程的实例；无数个流程实例（如员工提交的请假单审批流程）
3. 流程变量：（ProcessVariable）：  
    流程运行提交的数据，共享的数据，等；
4. 任务：（Task）  
    每一步需要完成的工作算是一个任务；（每一个流程运行都需要完成自己的流程规定的任务）
5. 开始、结束（Start 、End）  
    每一个流程只有一个入口，无数的出口；（正常结束的出口，异常结束的出口等）
6. 网关（Gateway）：  
    流程的分支节点（判断节点）；决定下一步走哪儿；
    
## activiti5
- ProcessEngine：Activiti5的核心
- repositoryService（持久化服务）：
- runtimeService（运行时服务）
- formService（表单服务）：
- identityService（身份信息）：
- taskService（任务服务）：
- historyService（历史信息）：
- managementService（管理定时任务）：

## Activiti5框架表结构
Activiti使用到的表都是ACT_开头的。  
- ACT_RE_*: ‘RE’表示repository(存储)，RepositoryService接口所操作的表。带此前缀的表包含的是静态信息，如，流程定义，流程的资源（图片，规则等）。
- ACT_RU_*: ‘RU’表示runtime，运行时表-RuntimeService。这是运行时的表存储着流程变量，用户任务，变量，职责（job）等运行时的数据。Activiti只存储实例执行期间的运行时数据，当流程实例结束时，将删除这些记录。这就保证了这些运行时的表小且快。
- ACT_ID_*: ’ID’表示identity (组织机构)，IdentityService接口所操作的表。用户记录，流程中使用到的用户和组。这些表包含标识的信息，如用户，用户组，等等。
- ACT_HI_*: ’HI’表示history，历史数据表，HistoryService。就是这些表包含着流程执行的历史相关数据，如结束的流程实例，变量，任务，等等
- ACT_GE_*: 全局通用数据及设置(general)，各种情况都使用的数据。

## 所有表
- act_ge_bytearray：二进制数据表
- act_ge_property：属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录，
- act_hi_actinst：历史节点表
- act_hi_attachment：历史附件表
- act_hi_comment：历史意见表
- act_hi_identitylink：历史流程人员表
- act_hi_detail：历史详情表，提供历史变量的查询
- act_hi_procinst：历史流程实例表
- act_hi_taskinst：历史任务实例表
- act_hi_varinst：历史变量表
- act_id_group：用户组信息表
- act_id_info：用户扩展信息表
- act_id_membership：用户与用户组对应信息表
- act_id_user：用户信息表
- act_re_deployment：部署信息表
- act_re_model：流程设计模型部署表
- act_re_procdef：流程定义数据表
- act_ru_event_subscr ： throwEvent、catchEvent时间监听信息表
- act_ru_execution：运行时流程执行实例表
- act_ru_identitylink：运行时流程人员表，主要存储任务节点与参与者的相关信息
- act_ru_job：运行时定时任务数据表
- act_ru_task：运行时任务节点表
- act_ru_variable：运行时流程变量数据表