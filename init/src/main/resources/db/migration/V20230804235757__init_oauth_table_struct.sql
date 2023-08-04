/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : redbook

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 05/08/2023 00:22:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `resource_ids`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `client_secret`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `scope`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `authorized_grant_types`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `authorities`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    `access_token_validity`   int                                                             DEFAULT NULL,
    `refresh_token_validity`  int                                                             DEFAULT NULL,
    `additional_information`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
    `create_time`             timestamp                                              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `archived`                tinyint                                                         DEFAULT NULL,
    `trusted`                 tinyint                                                         DEFAULT NULL,
    `autoapprove`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin          DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`,
                                    `web_server_redirect_uri`, `authorities`, `access_token_validity`,
                                    `refresh_token_validity`, `additional_information`, `create_time`, `archived`,
                                    `trusted`, `autoapprove`)
VALUES ('myjob', 'myjob', '$2a$10$Dappn49nM8ZjkCO3ASwR/evnHQN3iPH4npP45js0d9Ug3Vm4ui.sq', 'ROLE_API',
        'authorization_code,password,client_credentials,implicit,refresh_token', 'https://www.baidu.com', NULL, 7200,
        259200, NULL, '2020-12-13 15:53:10', 0, 0, 'false');
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `code`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `create_time`    timestamp NULL                                         DEFAULT CURRENT_TIMESTAMP,
    `authentication` blob
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
INSERT INTO `oauth_code` (`code`, `create_time`, `authentication`)
VALUES ('Ykj769', '2020-12-13 16:18:25',
        0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A65787000000002770400000002737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F7269747900000000000001FE0200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B787074000270317371007E000D74000270337871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B726564697265637455726971007E000E4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0016787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000E4C001172657175657374506172616D657465727371007E00144C000573636F706571007E001678707400026331737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E00147870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F400000000000067708000000080000000474000D726573706F6E73655F74797065740004636F6465740009636C69656E745F696471007E001974000C72656469726563745F75726C74001568747470733A2F2F7777772E62616964752E636F6D74000573636F706574000A524F4C455F41444D494E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F4000000000000171007E002478017371007E0028770C000000103F40000000000000787371007E001C3F40000000000000770800000010000000007874001568747470733A2F2F7777772E62616964752E636F6D707371007E0028770C000000103F4000000000000174000472657331787371007E0028770C000000103F4000000000000171007E001F787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000001FE0200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B0000000277040000000271007E000F71007E00117871007E0033737200486F72672E737072696E676672616D65776F726B2E73656375726974792E7765622E61757468656E7469636174696F6E2E57656241757468656E7469636174696F6E44657461696C7300000000000001FE0200024C000D72656D6F74654164647265737371007E000E4C000973657373696F6E496471007E000E787074000F303A303A303A303A303A303A303A31740020413742323638324643314142443130313035324444414341353444413343453170737200326F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657200000000000001FE0200075A00116163636F756E744E6F6E457870697265645A00106163636F756E744E6F6E4C6F636B65645A001563726564656E7469616C734E6F6E457870697265645A0007656E61626C65644C000B617574686F72697469657371007E00164C000870617373776F726471007E000E4C0008757365726E616D6571007E000E7870010101017371007E0025737200116A6176612E7574696C2E54726565536574DD98509395ED875B0300007870737200466F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657224417574686F72697479436F6D70617261746F7200000000000001FE020000787077040000000271007E000F71007E00117870740008746F6D7368696469);
INSERT INTO `oauth_code` (`code`, `create_time`, `authentication`)
VALUES ('m1RA0S', '2021-03-15 15:13:11',
        0xACED0005737200416F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F417574683241757468656E7469636174696F6EBD400B02166252130200024C000D73746F7265645265717565737474003C4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F4F4175746832526571756573743B4C00127573657241757468656E7469636174696F6E7400324C6F72672F737072696E676672616D65776F726B2F73656375726974792F636F72652F41757468656E7469636174696F6E3B787200476F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E416273747261637441757468656E7469636174696F6E546F6B656ED3AA287E6E47640E0200035A000D61757468656E746963617465644C000B617574686F7269746965737400164C6A6176612F7574696C2F436F6C6C656374696F6E3B4C000764657461696C737400124C6A6176612F6C616E672F4F626A6563743B787000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C6973747400104C6A6176612F7574696C2F4C6973743B7872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C00016371007E00047870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A65787000000002770400000002737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F7269747900000000000002080200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B787074000270317371007E000D74000270337871007E000C707372003A6F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E4F41757468325265717565737400000000000000010200075A0008617070726F7665644C000B617574686F72697469657371007E00044C000A657874656E73696F6E7374000F4C6A6176612F7574696C2F4D61703B4C000B726564697265637455726971007E000E4C00077265667265736874003B4C6F72672F737072696E676672616D65776F726B2F73656375726974792F6F61757468322F70726F76696465722F546F6B656E526571756573743B4C000B7265736F7572636549647374000F4C6A6176612F7574696C2F5365743B4C000D726573706F6E7365547970657371007E0016787200386F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E70726F76696465722E426173655265717565737436287A3EA37169BD0200034C0008636C69656E74496471007E000E4C001172657175657374506172616D657465727371007E00144C000573636F706571007E001678707400026331737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170F1A5A8FE74F507420200014C00016D71007E00147870737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F400000000000067708000000080000000474000D726573706F6E73655F74797065740004636F646574000C72656469726563745F75726974001568747470733A2F2F7777772E62616964752E636F6D740009636C69656E745F696471007E001974000573636F706574000A524F4C455F41444D494E78737200256A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574801D92D18F9B80550200007871007E0009737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000103F4000000000000171007E002478017371007E0028770C000000103F40000000000000787371007E001C3F40000000000000770800000010000000007874001568747470733A2F2F7777772E62616964752E636F6D707371007E0028770C000000103F4000000000000174000472657331787371007E0028770C000000103F4000000000000171007E001F787372004F6F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E00000000000002080200024C000B63726564656E7469616C7371007E00054C00097072696E636970616C71007E00057871007E0003017371007E00077371007E000B0000000277040000000271007E000F71007E00117871007E0033737200486F72672E737072696E676672616D65776F726B2E73656375726974792E7765622E61757468656E7469636174696F6E2E57656241757468656E7469636174696F6E44657461696C7300000000000002080200024C000D72656D6F74654164647265737371007E000E4C000973657373696F6E496471007E000E787074000F303A303A303A303A303A303A303A31740020423332374337354343343734363741384638333831393633373530363330414670737200326F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657200000000000002080200075A00116163636F756E744E6F6E457870697265645A00106163636F756E744E6F6E4C6F636B65645A001563726564656E7469616C734E6F6E457870697265645A0007656E61626C65644C000B617574686F72697469657371007E00164C000870617373776F726471007E000E4C0008757365726E616D6571007E000E7870010101017371007E0025737200116A6176612E7574696C2E54726565536574DD98509395ED875B0300007870737200466F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E7573657264657461696C732E5573657224417574686F72697479436F6D70617261746F720000000000000208020000787077040000000271007E000F71007E001178707400727B2266756C6C6E616D65223A22222C226964223A2233222C2270617373776F7264223A222432612431302458427155412F455A6B6D494579485167554D644B6B6537696831376155422F35677A62756C356B525964767A644D72657A53794C79222C22757365726E616D65223A226865227D);
COMMIT;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
    `resource_id`     int NOT NULL AUTO_INCREMENT COMMENT '资源id',
    `resource_name`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '资源名称',
    `resource_type`   varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '资源类型0:系统 1:模块 2：URL',
    `resource_url`    varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源url',
    `resource_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '资源请求方式',
    `parent_id`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '父级资源id，为0代表顶级',
    `available`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT '1' COMMENT '可用:1，不可用:0',
    `resource_order`  int                                                    DEFAULT NULL COMMENT '资源排序',
    `resource_icon`   varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源图标',
    `resource_info`   varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源名称详细描述',
    `resource_code`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源编号/别名',
    PRIMARY KEY (`resource_id`) USING BTREE,
    KEY `resource_parentid` (`parent_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (1, 'test1', '1', '/orderSer/test/testMethodA', 'GET', '0', '1', NULL, NULL, NULL, 'testa');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (2, 'test2', '1', '/orderSer/test/testMethodB', 'GET', '0', '1', NULL, NULL, NULL, 'testb');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (3, NULL, '1', '/oa/index', 'GET', '0', '1', NULL, NULL, NULL, 'index');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (4, NULL, '1', '/oa/l1/1', 'GET', '0', '1', NULL, NULL, NULL, 'vip1');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (5, NULL, '1', '/oa/l1/2', 'GET', '0', '1', NULL, NULL, NULL, 'vip1');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (6, NULL, '1', '/oa/l1/3', 'GET', '0', '1', NULL, NULL, NULL, 'vip1');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (7, NULL, '1', '/oa/l2/1', 'GET', '0', '1', NULL, NULL, NULL, 'vip2');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (8, NULL, '1', '/oa/l2/2', 'GET', '0', '1', NULL, NULL, NULL, 'vip2');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (9, NULL, '1', '/oa/l2/3', 'GET', '0', '1', NULL, NULL, NULL, 'vip2');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (10, NULL, '1', '/oa/l3/1', 'GET', '0', '1', NULL, NULL, NULL, 'vip3');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (11, NULL, '1', '/oa/l3/2', 'GET', '0', '1', NULL, NULL, NULL, 'vip3');
INSERT INTO `resource` (`resource_id`, `resource_name`, `resource_type`, `resource_url`, `resource_method`, `parent_id`,
                        `available`, `resource_order`, `resource_icon`, `resource_info`, `resource_code`)
VALUES (12, NULL, '1', '/oa/l3/3', 'GET', '0', '1', NULL, NULL, NULL, 'vip3');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`        bigint                                                 NOT NULL,
    `name`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `authority`)
VALUES (1, '管理员', 'admin');
INSERT INTO `role` (`id`, `name`, `authority`)
VALUES (2, '普通用户', 'normal');
COMMIT;

-- ----------------------------
-- Table structure for role_resource_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_rel`;
CREATE TABLE `role_resource_rel`
(
    `role_id`     bigint NOT NULL COMMENT '角色id',
    `resource_id` bigint NOT NULL COMMENT '资源id',
    PRIMARY KEY (`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_resource_rel
-- ----------------------------
BEGIN;
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 1);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 2);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 3);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 4);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 5);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (1, 6);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (2, 1);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (2, 3);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (2, 7);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (2, 8);
INSERT INTO `role_resource_rel` (`role_id`, `resource_id`)
VALUES (2, 9);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL,
    `password`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `role`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL,
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `user_name`, `password`, `role`)
VALUES ('1354255569179971599', '11111user', '$2a$10$kNL92rkDomHmdzjHK35JM.fqxrbJUaTzyu2N70LP.Xjwg6.nrH6lG', '管理员');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `role`)
VALUES ('1354255569179971699', 'xxxx@qq.com', '$2a$10$kNL92rkDomHmdzjHK35JM.fqxrbJUaTzyu2N70LP.Xjwg6.nrH6lG',
        '普通用户');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;