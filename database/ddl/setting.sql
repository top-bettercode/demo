# 系统基础
# js_administrative_area
DROP TABLE IF EXISTS `js_administrative_area`;
CREATE TABLE `js_administrative_area` (
  `area_id` BIGINT(20) AUTO_INCREMENT NOT NULL COMMENT '行政区划主键ID',
  `area_code` VARCHAR(200) NULL COMMENT '行政区代码',
  `area_name` VARCHAR(200) NULL COMMENT '行政区名称',
  `area_path` TEXT NULL COMMENT '行政区路径，">"分隔，示例：湖北省>黄冈市',
  `area_parent_code` VARCHAR(200) DEFAULT '-1' NULL COMMENT '行政区父类代码',
  `area_level` INT(11) NULL COMMENT '行政区级别',
  `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
  PRIMARY KEY (`area_id`)
) COMMENT = '行政区划';

