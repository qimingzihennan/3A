/*
MySQL Backup
Source Server Version: 5.7.13
Source Database: zhongcaiwei
Date: 2016-8-30 09:56:41
*/
DROP TABLE IF EXISTS `T_User`;
DROP TABLE IF EXISTS `T_Role`;
DROP TABLE IF EXISTS `T_UserRole`;
DROP TABLE IF EXISTS `T_RoleResources`;
DROP TABLE IF EXISTS `T_Business`;
DROP TABLE IF EXISTS `T_BusinessExtendField`;
DROP TABLE IF EXISTS `T_Combo`;
DROP TABLE IF EXISTS `T_ComboExtendField`;
DROP TABLE IF EXISTS `T_Order`;
DROP TABLE IF EXISTS `T_CusConsumeInventory`;
DROP TABLE IF EXISTS `T_CusConsumeLog`;
DROP TABLE IF EXISTS `T_Personal`;
DROP TABLE IF EXISTS `T_Logs`; 
DROP TABLE IF EXISTS `T_Enterprise`;
DROP TABLE IF EXISTS `T_PIN_SD`;
DROP TABLE IF EXISTS `T_Relation`; 
DROP TABLE IF EXISTS `T_Register`; 
DROP TABLE IF EXISTS `T_File`; 
DROP TABLE IF EXISTS `T_OrderExtendField`; 
DROP TABLE IF EXISTS `T_ExcuteLog`;
DROP TABLE IF EXISTS `T_TaskJob`;

-- 后端人员管理
CREATE TABLE `T_User` (
`userId`                     int(11)             NOT NULL AUTO_INCREMENT       COMMENT '主键id',
`userName`                   varchar(20)         NOT NULL                      COMMENT '用户名',
`password`                   varchar(40)         NOT NULL                      COMMENT '密码',
`status`                     varchar(20)         NULL                          COMMENT '状态(0 停止 1 正常)',
`realName`                   varchar(40)         NOT NULL                      COMMENT '用户姓名',
`sex`                        varchar(10)         NULL                          COMMENT '性别(1 男 2 女)',
`mobile`                     varchar(100)        NULL                          COMMENT '用户移动电话',
`email`                      varchar(100)        NULL                          COMMENT '用户名邮箱地址',
`isSuper`                    varchar(10)         NULL                          COMMENT '是否为超级管理员',
`delFlag`                    varchar(10)         NULL                          COMMENT '删除标志位  0 代表未删除 1 代表已删除',
PRIMARY KEY (`userId`) 
)
AUTO_INCREMENT=0
COMMENT='用户管理表机构';

-- 角色表
CREATE TABLE `T_Role` (
`roleId` 					 int(11) 			 NOT NULL AUTO_INCREMENT 	   COMMENT '主键id',
`name` 						 varchar(50) 		 NULL 						   COMMENT '角色名称',
`description`  				 varchar(255) 		 NULL 						   COMMENT '角色描述',
`delFlag` 					 varchar(10) 		 NULL 						   COMMENT '删除标志位  0 代表未删除 1 代表已删除',
PRIMARY KEY (`roleId`) 
)
AUTO_INCREMENT=1
COMMENT='角色表';

-- 用户角色关系表
CREATE TABLE `T_UserRole` (
`USER_ID` 					 int 				 NOT NULL 					   COMMENT '用户id',
`roleId` 					 int 				 NOT NULL 					   COMMENT '组织id'
)
COMMENT='用户角色关系表';

-- 角色资源管理后台用户功能权限设定
CREATE TABLE `T_RoleResources` (
`roleId` 					 int(11) 			 NOT NULL 					   COMMENT '角色Id',
`resourcesName` 			 varchar(255) 		 NULL 						   COMMENT '菜单功能名称',
`url` 						 varchar(255) 		 NULL 						   COMMENT '功能管理Controller的Url头'
)
COMMENT='角色资源管理后台用户功能权限设定';

-- 业务模块
CREATE TABLE `T_Business` (
`businessName` 			 varchar(255) 		 NULL 						   COMMENT '业务模块名称',
`businessDes` 			 varchar(255) 		 NULL 						   COMMENT '业务模块描述',
`Bkey` 			        varchar(255) 		 NULL 						   COMMENT '业务模块key',
`del` 			        varchar(255) 		 NULL 						   COMMENT '删除标志 1 已删除'
)
COMMENT='业务模块';

-- 业务模块扩充字段
CREATE TABLE `T_BusinessExtendField` (
`BEFName` 			 varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称',
`Bkey` 			        varchar(255) 		 NULL 						   COMMENT '业务模块key',
`eName` 			 varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称英文表示'
)
COMMENT='业务模块扩充字段';

-- 套餐表
CREATE TABLE `T_Combo` (
  `id`                              int(11)              NOT NULL AUTO_INCREMENT        COMMENT '主键',
  `Bkey` 			                varchar(255) 		 NULL 						    COMMENT '业务模块key',
  `name`                            varchar(50)          DEFAULT NULL                   COMMENT '套餐名称',
  `number`                          int(10)              DEFAULT NULL                   COMMENT '次数',
  `price`                           double(10,2)         DEFAULT NULL                   COMMENT '价格',
  `coType`                           int(1)               DEFAULT NULL                  COMMENT '类型1个人2企业',
  `days`                            int(10)              DEFAULT NULL                   COMMENT '有效天数',
  `space`                            int(10)              DEFAULT NULL                   COMMENT '储存空间',
  `content`                         text         		 DEFAULT NULL                   COMMENT '套餐内容',
  `paidMode`                        varchar(50)          DEFAULT NULL                   COMMENT '计费模式 1次数、2天数、3次数+天数、4储存空间',
  `status`                          int(1)               DEFAULT NULL                   COMMENT '状态0禁用1启用2取消',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐';

-- 套餐字段扩展表
CREATE TABLE `T_ComboExtendField` (
`coId`                              int(11)              NOT NULL                    COMMENT '套餐id',
`BEFName` 			              varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称',
`BEFValue` 			              varchar(255) 		 NULL 						   COMMENT '业务字段值',
`eName` 			 varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称英文表示'
)
COMMENT='套餐字段扩展表';

-- 用户订单表
CREATE TABLE `T_Order` (
  `id`                             int(11)               NOT NULL AUTO_INCREMENT                  COMMENT '主键',
  `orderNO`                        varchar(50)           DEFAULT NULL						  COMMENT '订单编号',
  `businessName` 			       varchar(255) 		 NULL 						              COMMENT '业务模块名称',
  `comboId`                        int(11)               DEFAULT NULL                             COMMENT '套餐ID',
  `comboName`                      varchar(50)           DEFAULT NULL                             COMMENT '套餐名称',
   `price`                          double(10,2)          DEFAULT NULL                            COMMENT '实际价格',
  `number`                         int(10)               DEFAULT NULL                             COMMENT '次数',
  `days`                           int(10)               DEFAULT NULL                   		  COMMENT '天数',
  `space`                            int(10)              DEFAULT NULL                   COMMENT '储存空间',
   `startTime` 					   datetime   			 DEFAULT NULL 					          COMMENT '开始时间',
  `endTime` 					   datetime 			 DEFAULT NULL 							  COMMENT '结束时间',
  `content`                        text         		 DEFAULT NULL                             COMMENT '套餐内容',
  `cusId`                     int(11)                    DEFAULT NULL                             COMMENT '客户ID/企业ID',
  `orderType` 						    varchar(50)      DEFAULT NULL                             COMMENT '1个人订单、2企业订单',
  `orderTime`                      timestamp NULL        DEFAULT NULL 							  COMMENT '下单时间',
  `payTime` 					   timestamp NULL        DEFAULT NULL                             COMMENT '支付时间', 
  `paidMode`                        varchar(50)          DEFAULT NULL                             COMMENT '计费模式',
  `status` 						    varchar(50)                 DEFAULT NULL                             COMMENT '状态0待缴费1已交费2取消',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订单表';

-- 用户消费清单表
CREATE TABLE `T_CusConsumeInventory` (
  `id`                              int(11)              NOT NULL AUTO_INCREMENT        COMMENT '主键',
  `businessName` 			       varchar(255) 		 NULL 						    COMMENT '业务模块名称',
  `Bkey` 			                varchar(255) 		 NULL 						    COMMENT '业务模块key',
  `cusId`                     int(11)                    DEFAULT NULL                   COMMENT '客户ID',
  `totalNumber`                          int(10)        DEFAULT NULL                    COMMENT '总次数',
  `residueNumber`                          int(10)      DEFAULT NULL                    COMMENT '剩余次数',
   `startTime` 					   datetime   			 DEFAULT NULL 					COMMENT '开始时间',
  `endTime` 					   datetime 			 DEFAULT NULL 				    COMMENT '结束时间',
  `paidMode`                        varchar(50)          DEFAULT NULL                   COMMENT '计费模式',
  `status`                          varchar(1)               DEFAULT NULL                   COMMENT ' 状态0正在使用、1暂停、2待使用、3完成消费',
  `orderId`                          int(11)               DEFAULT NULL                   COMMENT '订单id',
   `orderType`                          varchar(20)               DEFAULT NULL                   COMMENT '类型1个人2企业',
   `PIN`                          varchar(200)               DEFAULT NULL                   COMMENT '企业PIN码',
   `createTime`                         datetime               DEFAULT NULL                   COMMENT '数据生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消费清单表';

-- 用户消费日志
CREATE TABLE `T_CusConsumeLog` (
  `id`                              int(11)              NOT NULL         COMMENT '用户消费清单表id',
  `cusIdOrPIN`                     		varchar(200)                 DEFAULT NULL                   COMMENT '客户ID,企业PIN码，企业id',
   `operateTime` 					   datetime   			 DEFAULT NULL 					COMMENT '操作时间',
   `cusType`                          varchar(200)               DEFAULT NULL                   COMMENT '个人/企业 person/enterprise'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消费日志';
-- 个人用户管理
CREATE TABLE `T_Personal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `customerName` varchar(40) DEFAULT NULL COMMENT '用户名称',
  `mobile`       varchar(20)         DEFAULT NULL                  COMMENT '手机号',
  `email`        varchar(200)         DEFAULT NULL                  COMMENT '邮箱地址',
  `idCard` 		 varchar(50) 		 DEFAULT NULL  				   COMMENT '身份证证件号',
  `address` 	 varchar(200) 		 DEFAULT NULL 				   COMMENT '地址',
  `status` 		 int(1)		         DEFAULT NULL 				   COMMENT '状态(0未认证 1认证成功 2认证驳回 3待审核)',
  `delStatus` 		 int(1)		         DEFAULT NULL 				   COMMENT '删除',
`postcode` 	 varchar(50) 		 DEFAULT NULL 				   COMMENT '邮政编码',
`createTime`      datetime               DEFAULT NULL                   COMMENT '数据生成时间',
`approvalTime`                 datetime               DEFAULT NULL               COMMENT '数据认证操作时间',
 `Bkey` 					 varchar(255)  		  DEFAULT NULL 					   COMMENT '业务数据来源（业务key）',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- 日志管理
	
	CREATE TABLE `T_Logs` (
	  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
	  `accountName` varchar(200) DEFAULT NULL COMMENT '方法操作人',
	  `module` varchar(200) DEFAULT NULL COMMENT '操作模块',
	  `methods` varchar(200) DEFAULT NULL COMMENT '操作方法',
	  `description` varchar(200) DEFAULT NULL COMMENT '操作描述',
	  `actionTime` varchar(200) DEFAULT NULL COMMENT '操作用时',
	  `userIP` varchar(200) DEFAULT NULL COMMENT '用户ip',
	  `nowTime` datetime NOT NULL COMMENT '操作时间',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
-- 订单字段扩展表
CREATE TABLE `T_OrderExtendField` (
`oId`                              int(11)              NOT NULL                    COMMENT '订单id',
`BEFName` 			              varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称',
`BEFValue` 			              varchar(255) 		 NULL 						   COMMENT '业务字段值',
`eName` 			 varchar(255) 		 NULL 						   COMMENT '业务模块扩充字段名称英文表示',
`eValue` 			 varchar(255) 		 NULL 						   COMMENT 'IP字段'
);

-- 企业表
CREATE TABLE `T_Enterprise` (
`enterpriseId` 			     int(11) 			 NOT NULL AUTO_INCREMENT 	   COMMENT '主键id',
`enterpriseName` 			 varchar(50) 		 DEFAULT NULL 				   COMMENT '企业名称',
`businessNumber` 			 varchar(50)  		 DEFAULT NULL 					   COMMENT '营业执照登记号',
`enterpriseAddress` 		 varchar(50) 		 DEFAULT NULL 				   COMMENT '地址',
`adminName` 				 varchar(50)  		 DEFAULT NULL 					   COMMENT '法人姓名',
`adminIdCard` 				 varchar(50)  		 DEFAULT NULL 					   COMMENT '法人身份证证件号',
`status` 					 varchar(1)  		 NOT NULL 					   COMMENT '状态(1认证成功 2认证驳回 3待审核)',
`telephone` 				 varchar(20)  		  DEFAULT NULL 					   COMMENT '企业固定电话',
`email` 					 varchar(255)  		  DEFAULT NULL 					   COMMENT '企业邮箱',
`postCode` 					 varchar(255)  		  DEFAULT NULL 					   COMMENT '邮编',
`uscCode` 					 varchar(255)  		  DEFAULT NULL 					   COMMENT '统一社会信用代码',
`delFlag`                    varchar(10)         NULL                          COMMENT '删除标志位  0 代表未删除 1 代表已删除',
`createTime`                  datetime               DEFAULT NULL               COMMENT '数据生成时间',
`approvalTime`                 datetime               DEFAULT NULL               COMMENT '数据认证操作时间',
`approveMode`               varchar(10)               DEFAULT NULL           COMMENT '认证方式 1营业执照 2统一社会信用代码',
`Bkey` 					 varchar(255)  		  DEFAULT NULL 					   COMMENT '业务数据来源（业务key）',
`orgCertificate` 		 varchar(255)  		  DEFAULT NULL 					   COMMENT '组织机构代码证',
`trCertificate` 		 varchar(255)  		  DEFAULT NULL 					   COMMENT '税务登记证',
`agentName` 			varchar(255)  		  DEFAULT NULL 					   COMMENT '代理人姓名',
`agentIdCard` 			varchar(255)  		  DEFAULT NULL 					   COMMENT '代理人身份证号',
`agentMobile` 			varchar(255)  		  DEFAULT NULL 					   COMMENT '代理人手机号',

PRIMARY KEY (`enterpriseId`) 
)
COMMENT='企业表';

-- 企业PIN码SD码关联表
CREATE TABLE `T_PIN_SD` (
  `id`					 int(11)			 NOT NULL 			AUTO_INCREMENT COMMENT '主键id',
  `enterpriseId` 		 int(11) 			 NOT NULL 	  					 COMMENT '企业id',
  `PIN` 				 varchar(255)  		  DEFAULT NULL 					 COMMENT 'PIN码',
  `SD` 					 varchar(255)  		  DEFAULT NULL 					 COMMENT 'SD码',
  `status` 				 varchar(1)  		 NOT NULL 					     COMMENT '状态(0不可用，1可用)',
  PRIMARY KEY (`id`)
)
COMMENT='企业PIN码SD码关联表';

-- 个人企业关联表
CREATE TABLE `T_Relation` (
`id` 				         int(11) 			 NOT NULL AUTO_INCREMENT 	   COMMENT '主键id',
`personId` 				     int(11) 			 NOT NULL 				       COMMENT '个人id',
`enterpriseId` 				 int(11) 			 NOT NULL 				       COMMENT '企业id',
`isAdmin` 					varchar(10) 		 NOT NULL 				       COMMENT '管理员标志位（0为普通员工，1为管理员）',
PRIMARY KEY (`id`) 
)
COMMENT='个人与企业关联表';

CREATE TABLE `T_File` (
`id` 						 int(11) 			 NOT NULL AUTO_INCREMENT,
`relationId` 				 int(11) 			 NOT NULL 				       COMMENT '个人id/企业id',
`registerType` 				 int(1)		         NOT NULL 				       COMMENT '类型(1个人2企业)',
`fileName` 					 varchar(100) 		 NOT NULL 				       COMMENT '文件名称',
`fileType` 					 int(2) 			 NOT NULL 					   COMMENT '文件类型(0 个人照片 1 个人身份证正面 2 个人身份证反面 3 企业营业执照 4 组织机构代码证图片 5 法人手持证件照 6 代理人手持证件照 7 税务登记证图片 8统一社会信用代码图片)',
`file` 						 longblob 										   COMMENT '文件',
PRIMARY KEY (`id`)
)
COMMENT='文件';

-- 前端客户管理
CREATE TABLE `T_Register` (
`registerId` 				 int(11) 			 NOT NULL AUTO_INCREMENT 	   COMMENT '注册用户id',
`customerId`                 int(11)             NOT NULL                      COMMENT '用户ID',
`loginType`                  varchar(11)         NOT NULL                      COMMENT '登录类型',
`loginCode` 				 varchar(50) 		 NOT NULL 				       COMMENT '登录帐号',
`password` 					 varchar(50) 		 NOT NULL 					   COMMENT '密码凭证（站内的保存密码，站外的不保存或保存token）',
`createTime` 				 datetime 			 NOT NULL 					   COMMENT '注册时间',
`status` 					 int(1) 		     NULL 					       COMMENT '状态(1未激活 2激活)',
PRIMARY KEY (`registerId`) 
)
COMMENT='前端用户';

-- 异常日志
CREATE TABLE `T_ExcuteLog` (
`id` 				 int(11) 			 NOT NULL AUTO_INCREMENT 	   COMMENT '日志ID',
`module`                 varchar(255)             NOT NULL                      COMMENT '操作模块',
`methods`                  varchar(255)         NOT NULL                      COMMENT '操作方法',
`description` 				 varchar(255) 		 NOT NULL 				       COMMENT '异常描述',
`actionTime` 					 varchar(255) 		 NOT NULL 					   COMMENT '操作用时',
`nowTime` 				 datetime 			 NOT NULL 					   COMMENT '操作时间',
`taskId` 				 int(11) 			 NOT NULL 					   COMMENT '定时器ID',
PRIMARY KEY (`id`) 
)
COMMENT='异常日志';
-- 定时任务
CREATE TABLE `T_TaskJob` (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `jobName` varchar(255) NOT NULL COMMENT '任务名称',
  `jobStatus` varchar(255) NOT NULL COMMENT '任务状态 是否启动任务',
  `jobDescribe` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY (`jobId`)
) COMMENT='定时任务';


INSERT INTO `T_User` (`userId`,`userName`,`password`,`status`,`realName`,`sex`,`mobile`,`email`,`isSuper`,`delFlag`) VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','1','zhanghongwei2','1','15140110064','123@123.com','1','0');

INSERT INTO `T_Business` (`businessName`,`businessDes`,`Bkey`,`del`) VALUES ('时间戳','这是时间戳','timestamp','0');
INSERT INTO `T_Business` (`businessName`,`businessDes`,`Bkey`,`del`) VALUES ('电子合同','这是电子合同','contract','0');
INSERT INTO `T_BusinessExtendField` (`BEFName`,`Bkey`,`eName`) VALUES ('IP数','timestamp','ipNum');
INSERT INTO `T_BusinessExtendField` (`BEFName`,`Bkey`,`eName`) VALUES ('并发数','timestamp','concurrency');
INSERT INTO `T_TaskJob` (`jobName`,`jobStatus`,`jobDescribe`) VALUES ('updateTask()','0','更新用户消费清单数据');
INSERT INTO `T_TaskJob` (`jobName`,`jobStatus`,`jobDescribe`) VALUES ('saveTask()','0','查询用户消费清单操作日志信息数据');


INSERT INTO `T_Business` (`businessName`,`businessDes`,`Bkey`,`del`) VALUES ('电子存证','这是电子存证系统','preservation','0');
