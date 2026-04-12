/*
 Navicat Premium Data Transfer

 Source Server         : nysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : examsystem

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/04/2025 03:14:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_areas
-- ----------------------------
DROP TABLE IF EXISTS `address_areas`;
CREATE TABLE `address_areas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `area_id` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '县id',
  `area` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '县名',
  `city_id` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3145 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '县表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_areas
-- ----------------------------
INSERT INTO `address_areas` VALUES (1, '110101', '东城区', '110100');
INSERT INTO `address_areas` VALUES (2, '110102', '西城区', '110100');
INSERT INTO `address_areas` VALUES (3, '110103', '崇文区', '110100');
INSERT INTO `address_areas` VALUES (4, '110104', '宣武区', '110100');
INSERT INTO `address_areas` VALUES (5, '110105', '朝阳区', '110100');
INSERT INTO `address_areas` VALUES (6, '110106', '丰台区', '110100');
INSERT INTO `address_areas` VALUES (7, '110107', '石景山区', '110100');
INSERT INTO `address_areas` VALUES (8, '110108', '海淀区', '110100');
INSERT INTO `address_areas` VALUES (9, '110109', '门头沟区', '110100');
INSERT INTO `address_areas` VALUES (10, '110111', '房山区', '110100');
INSERT INTO `address_areas` VALUES (11, '110112', '通州区', '110100');
INSERT INTO `address_areas` VALUES (12, '110113', '顺义区', '110100');
INSERT INTO `address_areas` VALUES (13, '110114', '昌平区', '110100');
INSERT INTO `address_areas` VALUES (14, '110115', '大兴区', '110100');
INSERT INTO `address_areas` VALUES (15, '110116', '怀柔区', '110100');
INSERT INTO `address_areas` VALUES (16, '110117', '平谷区', '110100');
INSERT INTO `address_areas` VALUES (17, '110228', '密云县', '110100');
INSERT INTO `address_areas` VALUES (18, '110229', '延庆县', '110100');
INSERT INTO `address_areas` VALUES (19, '120101', '和平区', '120100');
INSERT INTO `address_areas` VALUES (20, '120102', '河东区', '120100');
INSERT INTO `address_areas` VALUES (21, '120103', '河西区', '120100');
INSERT INTO `address_areas` VALUES (22, '120104', '南开区', '120100');
INSERT INTO `address_areas` VALUES (23, '120105', '河北区', '120100');
INSERT INTO `address_areas` VALUES (24, '120106', '红桥区', '120100');
INSERT INTO `address_areas` VALUES (25, '120107', '塘沽区', '120100');
INSERT INTO `address_areas` VALUES (26, '120108', '汉沽区', '120100');
INSERT INTO `address_areas` VALUES (27, '120109', '大港区', '120100');
INSERT INTO `address_areas` VALUES (28, '120110', '东丽区', '120100');
INSERT INTO `address_areas` VALUES (29, '120111', '西青区', '120100');
INSERT INTO `address_areas` VALUES (30, '120112', '津南区', '120100');
INSERT INTO `address_areas` VALUES (31, '120113', '北辰区', '120100');
INSERT INTO `address_areas` VALUES (32, '120114', '武清区', '120100');
INSERT INTO `address_areas` VALUES (33, '120115', '宝坻区', '120100');
INSERT INTO `address_areas` VALUES (34, '120221', '宁河县', '120100');
INSERT INTO `address_areas` VALUES (35, '120223', '静海县', '120100');
INSERT INTO `address_areas` VALUES (36, '120225', '蓟　县', '120100');
INSERT INTO `address_areas` VALUES (37, '130101', '市辖区', '130100');
INSERT INTO `address_areas` VALUES (38, '130102', '长安区', '130100');
INSERT INTO `address_areas` VALUES (39, '130103', '桥东区', '130100');
INSERT INTO `address_areas` VALUES (40, '130104', '桥西区', '130100');
INSERT INTO `address_areas` VALUES (41, '130105', '新华区', '130100');
INSERT INTO `address_areas` VALUES (42, '130107', '井陉矿区', '130100');
INSERT INTO `address_areas` VALUES (43, '130108', '裕华区', '130100');
INSERT INTO `address_areas` VALUES (44, '130121', '井陉县', '130100');
INSERT INTO `address_areas` VALUES (45, '130123', '正定县', '130100');
INSERT INTO `address_areas` VALUES (46, '130124', '栾城县', '130100');
INSERT INTO `address_areas` VALUES (47, '130125', '行唐县', '130100');
INSERT INTO `address_areas` VALUES (48, '130126', '灵寿县', '130100');
INSERT INTO `address_areas` VALUES (49, '130127', '高邑县', '130100');
INSERT INTO `address_areas` VALUES (50, '130128', '深泽县', '130100');
INSERT INTO `address_areas` VALUES (51, '130129', '赞皇县', '130100');
INSERT INTO `address_areas` VALUES (52, '130130', '无极县', '130100');
INSERT INTO `address_areas` VALUES (53, '130131', '平山县', '130100');
INSERT INTO `address_areas` VALUES (54, '130132', '元氏县', '130100');
INSERT INTO `address_areas` VALUES (55, '130133', '赵　县', '130100');
INSERT INTO `address_areas` VALUES (56, '130181', '辛集市', '130100');
INSERT INTO `address_areas` VALUES (57, '130182', '藁城市', '130100');
INSERT INTO `address_areas` VALUES (58, '130183', '晋州市', '130100');
INSERT INTO `address_areas` VALUES (59, '130184', '新乐市', '130100');
INSERT INTO `address_areas` VALUES (60, '130185', '鹿泉市', '130100');
INSERT INTO `address_areas` VALUES (61, '130201', '市辖区', '130200');
INSERT INTO `address_areas` VALUES (62, '130202', '路南区', '130200');
INSERT INTO `address_areas` VALUES (63, '130203', '路北区', '130200');
INSERT INTO `address_areas` VALUES (64, '130204', '古冶区', '130200');
INSERT INTO `address_areas` VALUES (65, '130205', '开平区', '130200');
INSERT INTO `address_areas` VALUES (66, '130207', '丰南区', '130200');
INSERT INTO `address_areas` VALUES (67, '130208', '丰润区', '130200');
INSERT INTO `address_areas` VALUES (68, '130223', '滦　县', '130200');
INSERT INTO `address_areas` VALUES (69, '130224', '滦南县', '130200');
INSERT INTO `address_areas` VALUES (70, '130225', '乐亭县', '130200');
INSERT INTO `address_areas` VALUES (71, '130227', '迁西县', '130200');
INSERT INTO `address_areas` VALUES (72, '130229', '玉田县', '130200');
INSERT INTO `address_areas` VALUES (73, '130230', '唐海县', '130200');
INSERT INTO `address_areas` VALUES (74, '130281', '遵化市', '130200');
INSERT INTO `address_areas` VALUES (75, '130283', '迁安市', '130200');
INSERT INTO `address_areas` VALUES (76, '130301', '市辖区', '130300');
INSERT INTO `address_areas` VALUES (77, '130302', '海港区', '130300');
INSERT INTO `address_areas` VALUES (78, '130303', '山海关区', '130300');
INSERT INTO `address_areas` VALUES (79, '130304', '北戴河区', '130300');
INSERT INTO `address_areas` VALUES (80, '130321', '青龙满族自治县', '130300');
INSERT INTO `address_areas` VALUES (81, '130322', '昌黎县', '130300');
INSERT INTO `address_areas` VALUES (82, '130323', '抚宁县', '130300');
INSERT INTO `address_areas` VALUES (83, '130324', '卢龙县', '130300');
INSERT INTO `address_areas` VALUES (84, '130401', '市辖区', '130400');
INSERT INTO `address_areas` VALUES (85, '130402', '邯山区', '130400');
INSERT INTO `address_areas` VALUES (86, '130403', '丛台区', '130400');
INSERT INTO `address_areas` VALUES (87, '130404', '复兴区', '130400');
INSERT INTO `address_areas` VALUES (88, '130406', '峰峰矿区', '130400');
INSERT INTO `address_areas` VALUES (89, '130421', '邯郸县', '130400');
INSERT INTO `address_areas` VALUES (90, '130423', '临漳县', '130400');
INSERT INTO `address_areas` VALUES (91, '130424', '成安县', '130400');
INSERT INTO `address_areas` VALUES (92, '130425', '大名县', '130400');
INSERT INTO `address_areas` VALUES (93, '130426', '涉　县', '130400');
INSERT INTO `address_areas` VALUES (94, '130427', '磁　县', '130400');
INSERT INTO `address_areas` VALUES (95, '130428', '肥乡县', '130400');
INSERT INTO `address_areas` VALUES (96, '130429', '永年县', '130400');
INSERT INTO `address_areas` VALUES (97, '130430', '邱　县', '130400');
INSERT INTO `address_areas` VALUES (98, '130431', '鸡泽县', '130400');
INSERT INTO `address_areas` VALUES (99, '130432', '广平县', '130400');
INSERT INTO `address_areas` VALUES (100, '130433', '馆陶县', '130400');
INSERT INTO `address_areas` VALUES (101, '130434', '魏　县', '130400');
INSERT INTO `address_areas` VALUES (102, '130435', '曲周县', '130400');
INSERT INTO `address_areas` VALUES (103, '130481', '武安市', '130400');
INSERT INTO `address_areas` VALUES (104, '130501', '市辖区', '130500');
INSERT INTO `address_areas` VALUES (105, '130502', '桥东区', '130500');
INSERT INTO `address_areas` VALUES (106, '130503', '桥西区', '130500');
INSERT INTO `address_areas` VALUES (107, '130521', '邢台县', '130500');
INSERT INTO `address_areas` VALUES (108, '130522', '临城县', '130500');
INSERT INTO `address_areas` VALUES (109, '130523', '内丘县', '130500');
INSERT INTO `address_areas` VALUES (110, '130524', '柏乡县', '130500');
INSERT INTO `address_areas` VALUES (111, '130525', '隆尧县', '130500');
INSERT INTO `address_areas` VALUES (118, '130532', '平乡县', '130500');
INSERT INTO `address_areas` VALUES (119, '130533', '威　县', '130500');
INSERT INTO `address_areas` VALUES (120, '130534', '清河县', '130500');
INSERT INTO `address_areas` VALUES (121, '130535', '临西县', '130500');
INSERT INTO `address_areas` VALUES (122, '130581', '南宫市', '130500');
INSERT INTO `address_areas` VALUES (123, '130582', '沙河市', '130500');
INSERT INTO `address_areas` VALUES (124, '130601', '市辖区', '130600');
INSERT INTO `address_areas` VALUES (125, '130602', '新市区', '130600');
INSERT INTO `address_areas` VALUES (126, '130603', '北市区', '130600');
INSERT INTO `address_areas` VALUES (127, '130604', '南市区', '130600');
INSERT INTO `address_areas` VALUES (128, '130621', '满城县', '130600');
INSERT INTO `address_areas` VALUES (129, '130622', '清苑县', '130600');
INSERT INTO `address_areas` VALUES (130, '130623', '涞水县', '130600');
INSERT INTO `address_areas` VALUES (131, '130624', '阜平县', '130600');
INSERT INTO `address_areas` VALUES (132, '130625', '徐水县', '130600');
INSERT INTO `address_areas` VALUES (133, '130626', '定兴县', '130600');
INSERT INTO `address_areas` VALUES (134, '130627', '唐　县', '130600');
INSERT INTO `address_areas` VALUES (135, '130628', '高阳县', '130600');
INSERT INTO `address_areas` VALUES (136, '130629', '容城县', '130600');
INSERT INTO `address_areas` VALUES (137, '130630', '涞源县', '130600');
INSERT INTO `address_areas` VALUES (138, '130631', '望都县', '130600');
INSERT INTO `address_areas` VALUES (139, '130632', '安新县', '130600');
INSERT INTO `address_areas` VALUES (146, '130681', '涿州市', '130600');
INSERT INTO `address_areas` VALUES (147, '130682', '定州市', '130600');
INSERT INTO `address_areas` VALUES (148, '130683', '安国市', '130600');
INSERT INTO `address_areas` VALUES (149, '130684', '高碑店市', '130600');
INSERT INTO `address_areas` VALUES (150, '130701', '市辖区', '130700');
INSERT INTO `address_areas` VALUES (151, '130702', '桥东区', '130700');
INSERT INTO `address_areas` VALUES (152, '130703', '桥西区', '130700');
INSERT INTO `address_areas` VALUES (153, '130705', '宣化区', '130700');
INSERT INTO `address_areas` VALUES (154, '130706', '下花园区', '130700');
INSERT INTO `address_areas` VALUES (155, '130721', '宣化县', '130700');
INSERT INTO `address_areas` VALUES (156, '130722', '张北县', '130700');
INSERT INTO `address_areas` VALUES (157, '130723', '康保县', '130700');
INSERT INTO `address_areas` VALUES (158, '130724', '沽源县', '130700');
INSERT INTO `address_areas` VALUES (159, '130725', '尚义县', '130700');
INSERT INTO `address_areas` VALUES (160, '130726', '蔚　县', '130700');
INSERT INTO `address_areas` VALUES (166, '130732', '赤城县', '130700');
INSERT INTO `address_areas` VALUES (167, '130733', '崇礼县', '130700');
INSERT INTO `address_areas` VALUES (168, '130801', '市辖区', '130800');
INSERT INTO `address_areas` VALUES (176, '130825', '隆化县', '130800');
INSERT INTO `address_areas` VALUES (177, '130826', '丰宁满族自治县', '130800');
INSERT INTO `address_areas` VALUES (178, '130827', '宽城满族自治县', '130800');
INSERT INTO `address_areas` VALUES (179, '130828', '围场满族蒙古族自治县', '130800');
INSERT INTO `address_areas` VALUES (180, '130901', '市辖区', '130900');
INSERT INTO `address_areas` VALUES (181, '130902', '新华区', '130900');
INSERT INTO `address_areas` VALUES (190, '130928', '吴桥县', '130900');
INSERT INTO `address_areas` VALUES (191, '130929', '献　县', '130900');
INSERT INTO `address_areas` VALUES (192, '130930', '孟村回族自治县', '130900');
INSERT INTO `address_areas` VALUES (193, '130981', '泊头市', '130900');
INSERT INTO `address_areas` VALUES (194, '130982', '任丘市', '130900');
INSERT INTO `address_areas` VALUES (195, '130983', '黄骅市', '130900');
INSERT INTO `address_areas` VALUES (196, '130984', '河间市', '130900');
INSERT INTO `address_areas` VALUES (197, '131001', '市辖区', '131000');
INSERT INTO `address_areas` VALUES (198, '131002', '安次区', '131000');
INSERT INTO `address_areas` VALUES (199, '131003', '广阳区', '131000');
INSERT INTO `address_areas` VALUES (200, '131022', '固安县', '131000');
INSERT INTO `address_areas` VALUES (201, '131023', '永清县', '131000');
INSERT INTO `address_areas` VALUES (202, '131024', '香河县', '131000');
INSERT INTO `address_areas` VALUES (203, '131025', '大城县', '131000');
INSERT INTO `address_areas` VALUES (204, '131026', '文安县', '131000');
INSERT INTO `address_areas` VALUES (205, '131028', '大厂回族自治县', '131000');
INSERT INTO `address_areas` VALUES (206, '131081', '霸州市', '131000');
INSERT INTO `address_areas` VALUES (207, '131082', '三河市', '131000');
INSERT INTO `address_areas` VALUES (208, '131101', '市辖区', '131100');
INSERT INTO `address_areas` VALUES (209, '131102', '桃城区', '131100');
INSERT INTO `address_areas` VALUES (217, '131128', '阜城县', '131100');
INSERT INTO `address_areas` VALUES (218, '131181', '冀州市', '131100');
INSERT INTO `address_areas` VALUES (219, '131182', '深州市', '131100');
INSERT INTO `address_areas` VALUES (220, '140101', '市辖区', '140100');
INSERT INTO `address_areas` VALUES (221, '140105', '小店区', '140100');
INSERT INTO `address_areas` VALUES (222, '140106', '迎泽区', '140100');
INSERT INTO `address_areas` VALUES (223, '140107', '杏花岭区', '140100');
INSERT INTO `address_areas` VALUES (224, '140108', '尖草坪区', '140100');
INSERT INTO `address_areas` VALUES (225, '140109', '万柏林区', '140100');
INSERT INTO `address_areas` VALUES (226, '140110', '晋源区', '140100');
INSERT INTO `address_areas` VALUES (227, '140121', '清徐县', '140100');
INSERT INTO `address_areas` VALUES (228, '140122', '阳曲县', '140100');
INSERT INTO `address_areas` VALUES (229, '140123', '娄烦县', '140100');
INSERT INTO `address_areas` VALUES (230, '140181', '古交市', '140100');
INSERT INTO `address_areas` VALUES (231, '140201', '市辖区', '140200');
INSERT INTO `address_areas` VALUES (232, '140202', '城　区', '140200');
INSERT INTO `address_areas` VALUES (233, '140203', '矿　区', '140200');
INSERT INTO `address_areas` VALUES (241, '140226', '左云县', '140200');
INSERT INTO `address_areas` VALUES (242, '140227', '大同县', '140200');
INSERT INTO `address_areas` VALUES (243, '140301', '市辖区', '140300');
INSERT INTO `address_areas` VALUES (244, '140302', '城　区', '140300');
INSERT INTO `address_areas` VALUES (245, '140303', '矿　区', '140300');
INSERT INTO `address_areas` VALUES (246, '140311', '郊　区', '140300');
INSERT INTO `address_areas` VALUES (247, '140321', '平定县', '140300');
INSERT INTO `address_areas` VALUES (248, '140322', '盂　县', '140300');
INSERT INTO `address_areas` VALUES (249, '140401', '市辖区', '140400');
INSERT INTO `address_areas` VALUES (250, '140402', '城　区', '140400');
INSERT INTO `address_areas` VALUES (257, '140427', '壶关县', '140400');
INSERT INTO `address_areas` VALUES (258, '140428', '长子县', '140400');
INSERT INTO `address_areas` VALUES (259, '140429', '武乡县', '140400');
INSERT INTO `address_areas` VALUES (260, '140430', '沁　县', '140400');
INSERT INTO `address_areas` VALUES (261, '140431', '沁源县', '140400');
INSERT INTO `address_areas` VALUES (262, '140481', '潞城市', '140400');
INSERT INTO `address_areas` VALUES (263, '140501', '市辖区', '140500');
INSERT INTO `address_areas` VALUES (264, '140502', '城　区', '140500');
INSERT INTO `address_areas` VALUES (265, '140521', '沁水县', '140500');
INSERT INTO `address_areas` VALUES (266, '140522', '阳城县', '140500');
INSERT INTO `address_areas` VALUES (267, '140524', '陵川县', '140500');
INSERT INTO `address_areas` VALUES (268, '140525', '泽州县', '140500');
INSERT INTO `address_areas` VALUES (269, '140581', '高平市', '140500');
INSERT INTO `address_areas` VALUES (270, '140601', '市辖区', '140600');
INSERT INTO `address_areas` VALUES (271, '140602', '朔城区', '140600');
INSERT INTO `address_areas` VALUES (272, '140603', '平鲁区', '140600');
INSERT INTO `address_areas` VALUES (273, '140621', '山阴县', '140600');
INSERT INTO `address_areas` VALUES (274, '140622', '应　县', '140600');
INSERT INTO `address_areas` VALUES (275, '140623', '右玉县', '140600');
INSERT INTO `address_areas` VALUES (276, '140624', '怀仁县', '140600');
INSERT INTO `address_areas` VALUES (277, '140701', '市辖区', '140700');
INSERT INTO `address_areas` VALUES (282, '140724', '昔阳县', '140700');
INSERT INTO `address_areas` VALUES (283, '140725', '寿阳县', '140700');
INSERT INTO `address_areas` VALUES (284, '140726', '太谷县', '140700');
INSERT INTO `address_areas` VALUES (285, '140727', '祁　县', '140700');
INSERT INTO `address_areas` VALUES (286, '140728', '平遥县', '140700');
INSERT INTO `address_areas` VALUES (287, '140729', '灵石县', '140700');
INSERT INTO `address_areas` VALUES (288, '140781', '介休市', '140700');
INSERT INTO `address_areas` VALUES (289, '140801', '市辖区', '140800');
INSERT INTO `address_areas` VALUES (290, '140802', '盐湖区', '140800');
INSERT INTO `address_areas` VALUES (298, '140828', '夏　县', '140800');
INSERT INTO `address_areas` VALUES (299, '140829', '平陆县', '140800');
INSERT INTO `address_areas` VALUES (300, '140830', '芮城县', '140800');
INSERT INTO `address_areas` VALUES (301, '140881', '永济市', '140800');
INSERT INTO `address_areas` VALUES (302, '140882', '河津市', '140800');
INSERT INTO `address_areas` VALUES (303, '140901', '市辖区', '140900');
INSERT INTO `address_areas` VALUES (304, '140902', '忻府区', '140900');
INSERT INTO `address_areas` VALUES (305, '140921', '定襄县', '140900');
INSERT INTO `address_areas` VALUES (306, '140922', '五台县', '140900');
INSERT INTO `address_areas` VALUES (307, '140923', '代　县', '140900');
INSERT INTO `address_areas` VALUES (308, '140924', '繁峙县', '140900');
INSERT INTO `address_areas` VALUES (309, '140925', '宁武县', '140900');
INSERT INTO `address_areas` VALUES (316, '140932', '偏关县', '140900');
INSERT INTO `address_areas` VALUES (317, '140981', '原平市', '140900');
INSERT INTO `address_areas` VALUES (318, '141001', '市辖区', '141000');
INSERT INTO `address_areas` VALUES (319, '141002', '尧都区', '141000');
INSERT INTO `address_areas` VALUES (320, '141021', '曲沃县', '141000');
INSERT INTO `address_areas` VALUES (324, '141025', '古　县', '141000');
INSERT INTO `address_areas` VALUES (330, '141031', '隰　县', '141000');
INSERT INTO `address_areas` VALUES (331, '141032', '永和县', '141000');
INSERT INTO `address_areas` VALUES (332, '141033', '蒲　县', '141000');
INSERT INTO `address_areas` VALUES (333, '141034', '汾西县', '141000');
INSERT INTO `address_areas` VALUES (334, '141081', '侯马市', '141000');
INSERT INTO `address_areas` VALUES (335, '141082', '霍州市', '141000');
INSERT INTO `address_areas` VALUES (336, '141101', '市辖区', '141100');
INSERT INTO `address_areas` VALUES (337, '141102', '离石区', '141100');
INSERT INTO `address_areas` VALUES (347, '141130', '交口县', '141100');
INSERT INTO `address_areas` VALUES (348, '141181', '孝义市', '141100');
INSERT INTO `address_areas` VALUES (349, '141182', '汾阳市', '141100');
INSERT INTO `address_areas` VALUES (350, '150101', '市辖区', '150100');
INSERT INTO `address_areas` VALUES (351, '150102', '新城区', '150100');
INSERT INTO `address_areas` VALUES (352, '150103', '回民区', '150100');
INSERT INTO `address_areas` VALUES (353, '150104', '玉泉区', '150100');
INSERT INTO `address_areas` VALUES (354, '150105', '赛罕区', '150100');
INSERT INTO `address_areas` VALUES (355, '150121', '土默特左旗', '150100');
INSERT INTO `address_areas` VALUES (356, '150122', '托克托县', '150100');
INSERT INTO `address_areas` VALUES (357, '150123', '和林格尔县', '150100');
INSERT INTO `address_areas` VALUES (358, '150124', '清水河县', '150100');
INSERT INTO `address_areas` VALUES (359, '150125', '武川县', '150100');
INSERT INTO `address_areas` VALUES (360, '150201', '市辖区', '150200');
INSERT INTO `address_areas` VALUES (361, '150202', '东河区', '150200');
INSERT INTO `address_areas` VALUES (362, '150203', '昆都仑区', '150200');
INSERT INTO `address_areas` VALUES (363, '150204', '青山区', '150200');
INSERT INTO `address_areas` VALUES (364, '150205', '石拐区', '150200');
INSERT INTO `address_areas` VALUES (365, '150206', '白云矿区', '150200');
INSERT INTO `address_areas` VALUES (366, '150207', '九原区', '150200');
INSERT INTO `address_areas` VALUES (367, '150221', '土默特右旗', '150200');
INSERT INTO `address_areas` VALUES (368, '150222', '固阳县', '150200');
INSERT INTO `address_areas` VALUES (369, '150223', '达尔罕茂明安联合旗', '150200');
INSERT INTO `address_areas` VALUES (370, '150301', '市辖区', '150300');
INSERT INTO `address_areas` VALUES (371, '150302', '海勃湾区', '150300');
INSERT INTO `address_areas` VALUES (372, '150303', '海南区', '150300');
INSERT INTO `address_areas` VALUES (373, '150304', '乌达区', '150300');
INSERT INTO `address_areas` VALUES (374, '150401', '市辖区', '150400');
INSERT INTO `address_areas` VALUES (375, '150402', '红山区', '150400');
INSERT INTO `address_areas` VALUES (376, '150403', '元宝山区', '150400');
INSERT INTO `address_areas` VALUES (377, '150404', '松山区', '150400');
INSERT INTO `address_areas` VALUES (378, '150421', '阿鲁科尔沁旗', '150400');
INSERT INTO `address_areas` VALUES (379, '150422', '巴林左旗', '150400');
INSERT INTO `address_areas` VALUES (380, '150423', '巴林右旗', '150400');
INSERT INTO `address_areas` VALUES (381, '150424', '林西县', '150400');
INSERT INTO `address_areas` VALUES (382, '150425', '克什克腾旗', '150400');
INSERT INTO `address_areas` VALUES (383, '150426', '翁牛特旗', '150400');
INSERT INTO `address_areas` VALUES (384, '150428', '喀喇沁旗', '150400');
INSERT INTO `address_areas` VALUES (385, '150429', '宁城县', '150400');
INSERT INTO `address_areas` VALUES (386, '150430', '敖汉旗', '150400');
INSERT INTO `address_areas` VALUES (387, '150501', '市辖区', '150500');
INSERT INTO `address_areas` VALUES (388, '150502', '科尔沁区', '150500');
INSERT INTO `address_areas` VALUES (389, '150521', '科尔沁左翼中旗', '150500');
INSERT INTO `address_areas` VALUES (390, '150522', '科尔沁左翼后旗', '150500');
INSERT INTO `address_areas` VALUES (391, '150523', '开鲁县', '150500');
INSERT INTO `address_areas` VALUES (392, '150524', '库伦旗', '150500');
INSERT INTO `address_areas` VALUES (393, '150525', '奈曼旗', '150500');
INSERT INTO `address_areas` VALUES (394, '150526', '扎鲁特旗', '150500');
INSERT INTO `address_areas` VALUES (395, '150581', '霍林郭勒市', '150500');
INSERT INTO `address_areas` VALUES (396, '150602', '东胜区', '150600');
INSERT INTO `address_areas` VALUES (397, '150621', '达拉特旗', '150600');
INSERT INTO `address_areas` VALUES (398, '150622', '准格尔旗', '150600');
INSERT INTO `address_areas` VALUES (399, '150623', '鄂托克前旗', '150600');
INSERT INTO `address_areas` VALUES (400, '150624', '鄂托克旗', '150600');
INSERT INTO `address_areas` VALUES (401, '150625', '杭锦旗', '150600');
INSERT INTO `address_areas` VALUES (402, '150626', '乌审旗', '150600');
INSERT INTO `address_areas` VALUES (403, '150627', '伊金霍洛旗', '150600');
INSERT INTO `address_areas` VALUES (404, '150701', '市辖区', '150700');
INSERT INTO `address_areas` VALUES (405, '150702', '海拉尔区', '150700');
INSERT INTO `address_areas` VALUES (406, '150721', '阿荣旗', '150700');
INSERT INTO `address_areas` VALUES (407, '150722', '莫力达瓦达斡尔族自治旗', '150700');
INSERT INTO `address_areas` VALUES (408, '150723', '鄂伦春自治旗', '150700');
INSERT INTO `address_areas` VALUES (409, '150724', '鄂温克族自治旗', '150700');
INSERT INTO `address_areas` VALUES (410, '150725', '陈巴尔虎旗', '150700');
INSERT INTO `address_areas` VALUES (411, '150726', '新巴尔虎左旗', '150700');
INSERT INTO `address_areas` VALUES (412, '150727', '新巴尔虎右旗', '150700');
INSERT INTO `address_areas` VALUES (413, '150781', '满洲里市', '150700');
INSERT INTO `address_areas` VALUES (414, '150782', '牙克石市', '150700');
INSERT INTO `address_areas` VALUES (415, '150783', '扎兰屯市', '150700');
INSERT INTO `address_areas` VALUES (416, '150784', '额尔古纳市', '150700');
INSERT INTO `address_areas` VALUES (417, '150785', '根河市', '150700');
INSERT INTO `address_areas` VALUES (418, '150801', '市辖区', '150800');
INSERT INTO `address_areas` VALUES (419, '150802', '临河区', '150800');
INSERT INTO `address_areas` VALUES (420, '150821', '五原县', '150800');
INSERT INTO `address_areas` VALUES (421, '150822', '磴口县', '150800');
INSERT INTO `address_areas` VALUES (422, '150823', '乌拉特前旗', '150800');
INSERT INTO `address_areas` VALUES (423, '150824', '乌拉特中旗', '150800');
INSERT INTO `address_areas` VALUES (424, '150825', '乌拉特后旗', '150800');
INSERT INTO `address_areas` VALUES (425, '150826', '杭锦后旗', '150800');
INSERT INTO `address_areas` VALUES (426, '150901', '市辖区', '150900');
INSERT INTO `address_areas` VALUES (427, '150902', '集宁区', '150900');
INSERT INTO `address_areas` VALUES (428, '150921', '卓资县', '150900');
INSERT INTO `address_areas` VALUES (429, '150922', '化德县', '150900');
INSERT INTO `address_areas` VALUES (430, '150923', '商都县', '150900');
INSERT INTO `address_areas` VALUES (431, '150924', '兴和县', '150900');
INSERT INTO `address_areas` VALUES (432, '150925', '凉城县', '150900');
INSERT INTO `address_areas` VALUES (433, '150926', '察哈尔右翼前旗', '150900');
INSERT INTO `address_areas` VALUES (434, '150927', '察哈尔右翼中旗', '150900');
INSERT INTO `address_areas` VALUES (435, '150928', '察哈尔右翼后旗', '150900');
INSERT INTO `address_areas` VALUES (436, '150929', '四子王旗', '150900');
INSERT INTO `address_areas` VALUES (437, '150981', '丰镇市', '150900');
INSERT INTO `address_areas` VALUES (438, '152201', '乌兰浩特市', '152200');
INSERT INTO `address_areas` VALUES (439, '152202', '阿尔山市', '152200');
INSERT INTO `address_areas` VALUES (440, '152221', '科尔沁右翼前旗', '152200');
INSERT INTO `address_areas` VALUES (441, '152222', '科尔沁右翼中旗', '152200');
INSERT INTO `address_areas` VALUES (442, '152223', '扎赉特旗', '152200');
INSERT INTO `address_areas` VALUES (443, '152224', '突泉县', '152200');
INSERT INTO `address_areas` VALUES (444, '152501', '二连浩特市', '152500');
INSERT INTO `address_areas` VALUES (445, '152502', '锡林浩特市', '152500');
INSERT INTO `address_areas` VALUES (446, '152522', '阿巴嘎旗', '152500');
INSERT INTO `address_areas` VALUES (447, '152523', '苏尼特左旗', '152500');
INSERT INTO `address_areas` VALUES (448, '152524', '苏尼特右旗', '152500');
INSERT INTO `address_areas` VALUES (449, '152525', '东乌珠穆沁旗', '152500');
INSERT INTO `address_areas` VALUES (450, '152526', '西乌珠穆沁旗', '152500');
INSERT INTO `address_areas` VALUES (451, '152527', '太仆寺旗', '152500');
INSERT INTO `address_areas` VALUES (452, '152528', '镶黄旗', '152500');
INSERT INTO `address_areas` VALUES (453, '152529', '正镶白旗', '152500');
INSERT INTO `address_areas` VALUES (454, '152530', '正蓝旗', '152500');
INSERT INTO `address_areas` VALUES (455, '152531', '多伦县', '152500');
INSERT INTO `address_areas` VALUES (456, '152921', '阿拉善左旗', '152900');
INSERT INTO `address_areas` VALUES (457, '152922', '阿拉善右旗', '152900');
INSERT INTO `address_areas` VALUES (458, '152923', '额济纳旗', '152900');
INSERT INTO `address_areas` VALUES (459, '210101', '市辖区', '210100');
INSERT INTO `address_areas` VALUES (460, '210102', '和平区', '210100');
INSERT INTO `address_areas` VALUES (461, '210103', '沈河区', '210100');
INSERT INTO `address_areas` VALUES (462, '210104', '大东区', '210100');
INSERT INTO `address_areas` VALUES (463, '210105', '皇姑区', '210100');
INSERT INTO `address_areas` VALUES (464, '210106', '铁西区', '210100');
INSERT INTO `address_areas` VALUES (465, '210111', '苏家屯区', '210100');
INSERT INTO `address_areas` VALUES (466, '210112', '东陵区', '210100');
INSERT INTO `address_areas` VALUES (467, '210113', '新城子区', '210100');
INSERT INTO `address_areas` VALUES (468, '210114', '于洪区', '210100');
INSERT INTO `address_areas` VALUES (469, '210122', '辽中县', '210100');
INSERT INTO `address_areas` VALUES (470, '210123', '康平县', '210100');
INSERT INTO `address_areas` VALUES (471, '210124', '法库县', '210100');
INSERT INTO `address_areas` VALUES (472, '210181', '新民市', '210100');
INSERT INTO `address_areas` VALUES (473, '210201', '市辖区', '210200');
INSERT INTO `address_areas` VALUES (474, '210202', '中山区', '210200');
INSERT INTO `address_areas` VALUES (475, '210203', '西岗区', '210200');
INSERT INTO `address_areas` VALUES (476, '210204', '沙河口区', '210200');
INSERT INTO `address_areas` VALUES (477, '210211', '甘井子区', '210200');
INSERT INTO `address_areas` VALUES (478, '210212', '旅顺口区', '210200');
INSERT INTO `address_areas` VALUES (479, '210213', '金州区', '210200');
INSERT INTO `address_areas` VALUES (480, '210224', '长海县', '210200');
INSERT INTO `address_areas` VALUES (481, '210281', '瓦房店市', '210200');
INSERT INTO `address_areas` VALUES (482, '210282', '普兰店市', '210200');
INSERT INTO `address_areas` VALUES (483, '210283', '庄河市', '210200');
INSERT INTO `address_areas` VALUES (484, '210301', '市辖区', '210300');
INSERT INTO `address_areas` VALUES (485, '210302', '铁东区', '210300');
INSERT INTO `address_areas` VALUES (486, '210303', '铁西区', '210300');
INSERT INTO `address_areas` VALUES (487, '210304', '立山区', '210300');
INSERT INTO `address_areas` VALUES (488, '210311', '千山区', '210300');
INSERT INTO `address_areas` VALUES (489, '210321', '台安县', '210300');
INSERT INTO `address_areas` VALUES (490, '210323', '岫岩满族自治县', '210300');
INSERT INTO `address_areas` VALUES (491, '210381', '海城市', '210300');
INSERT INTO `address_areas` VALUES (492, '210401', '市辖区', '210400');
INSERT INTO `address_areas` VALUES (493, '210402', '新抚区', '210400');
INSERT INTO `address_areas` VALUES (494, '210403', '东洲区', '210400');
INSERT INTO `address_areas` VALUES (495, '210404', '望花区', '210400');
INSERT INTO `address_areas` VALUES (496, '210411', '顺城区', '210400');
INSERT INTO `address_areas` VALUES (497, '210421', '抚顺县', '210400');
INSERT INTO `address_areas` VALUES (498, '210422', '新宾满族自治县', '210400');
INSERT INTO `address_areas` VALUES (499, '210423', '清原满族自治县', '210400');
INSERT INTO `address_areas` VALUES (500, '210501', '市辖区', '210500');
INSERT INTO `address_areas` VALUES (501, '210502', '平山区', '210500');
INSERT INTO `address_areas` VALUES (502, '210503', '溪湖区', '210500');
INSERT INTO `address_areas` VALUES (503, '210504', '明山区', '210500');
INSERT INTO `address_areas` VALUES (504, '210505', '南芬区', '210500');
INSERT INTO `address_areas` VALUES (505, '210521', '本溪满族自治县', '210500');
INSERT INTO `address_areas` VALUES (506, '210522', '桓仁满族自治县', '210500');
INSERT INTO `address_areas` VALUES (507, '210601', '市辖区', '210600');
INSERT INTO `address_areas` VALUES (508, '210602', '元宝区', '210600');
INSERT INTO `address_areas` VALUES (509, '210603', '振兴区', '210600');
INSERT INTO `address_areas` VALUES (510, '210604', '振安区', '210600');
INSERT INTO `address_areas` VALUES (511, '210624', '宽甸满族自治县', '210600');
INSERT INTO `address_areas` VALUES (512, '210681', '东港市', '210600');
INSERT INTO `address_areas` VALUES (513, '210682', '凤城市', '210600');
INSERT INTO `address_areas` VALUES (514, '210701', '市辖区', '210700');
INSERT INTO `address_areas` VALUES (515, '210702', '古塔区', '210700');
INSERT INTO `address_areas` VALUES (516, '210703', '凌河区', '210700');
INSERT INTO `address_areas` VALUES (517, '210711', '太和区', '210700');
INSERT INTO `address_areas` VALUES (518, '210726', '黑山县', '210700');
INSERT INTO `address_areas` VALUES (519, '210727', '义　县', '210700');
INSERT INTO `address_areas` VALUES (520, '210781', '凌海市', '210700');
INSERT INTO `address_areas` VALUES (521, '210782', '北宁市', '210700');
INSERT INTO `address_areas` VALUES (522, '210801', '市辖区', '210800');
INSERT INTO `address_areas` VALUES (523, '210802', '站前区', '210800');
INSERT INTO `address_areas` VALUES (524, '210803', '西市区', '210800');
INSERT INTO `address_areas` VALUES (525, '210804', '鲅鱼圈区', '210800');
INSERT INTO `address_areas` VALUES (526, '210811', '老边区', '210800');
INSERT INTO `address_areas` VALUES (527, '210881', '盖州市', '210800');
INSERT INTO `address_areas` VALUES (528, '210882', '大石桥市', '210800');
INSERT INTO `address_areas` VALUES (529, '210901', '市辖区', '210900');
INSERT INTO `address_areas` VALUES (530, '210902', '海州区', '210900');
INSERT INTO `address_areas` VALUES (531, '210903', '新邱区', '210900');
INSERT INTO `address_areas` VALUES (532, '210904', '太平区', '210900');
INSERT INTO `address_areas` VALUES (533, '210905', '清河门区', '210900');
INSERT INTO `address_areas` VALUES (534, '210911', '细河区', '210900');
INSERT INTO `address_areas` VALUES (535, '210921', '阜新蒙古族自治县', '210900');
INSERT INTO `address_areas` VALUES (536, '210922', '彰武县', '210900');
INSERT INTO `address_areas` VALUES (537, '211001', '市辖区', '211000');
INSERT INTO `address_areas` VALUES (538, '211002', '白塔区', '211000');
INSERT INTO `address_areas` VALUES (539, '211003', '文圣区', '211000');
INSERT INTO `address_areas` VALUES (540, '211004', '宏伟区', '211000');
INSERT INTO `address_areas` VALUES (541, '211005', '弓长岭区', '211000');
INSERT INTO `address_areas` VALUES (542, '211011', '太子河区', '211000');
INSERT INTO `address_areas` VALUES (543, '211021', '辽阳县', '211000');
INSERT INTO `address_areas` VALUES (544, '211081', '灯塔市', '211000');
INSERT INTO `address_areas` VALUES (545, '211101', '市辖区', '211100');
INSERT INTO `address_areas` VALUES (546, '211102', '双台子区', '211100');
INSERT INTO `address_areas` VALUES (547, '211103', '兴隆台区', '211100');
INSERT INTO `address_areas` VALUES (548, '211121', '大洼县', '211100');
INSERT INTO `address_areas` VALUES (549, '211122', '盘山县', '211100');
INSERT INTO `address_areas` VALUES (550, '211201', '市辖区', '211200');
INSERT INTO `address_areas` VALUES (551, '211202', '银州区', '211200');
INSERT INTO `address_areas` VALUES (552, '211204', '清河区', '211200');
INSERT INTO `address_areas` VALUES (553, '211221', '铁岭县', '211200');
INSERT INTO `address_areas` VALUES (554, '211223', '西丰县', '211200');
INSERT INTO `address_areas` VALUES (555, '211224', '昌图县', '211200');
INSERT INTO `address_areas` VALUES (556, '211281', '调兵山市', '211200');
INSERT INTO `address_areas` VALUES (557, '211282', '开原市', '211200');
INSERT INTO `address_areas` VALUES (558, '211301', '市辖区', '211300');
INSERT INTO `address_areas` VALUES (559, '211302', '双塔区', '211300');
INSERT INTO `address_areas` VALUES (560, '211303', '龙城区', '211300');
INSERT INTO `address_areas` VALUES (561, '211321', '朝阳县', '211300');
INSERT INTO `address_areas` VALUES (562, '211322', '建平县', '211300');
INSERT INTO `address_areas` VALUES (563, '211324', '喀喇沁左翼蒙古族自治县', '211300');
INSERT INTO `address_areas` VALUES (564, '211381', '北票市', '211300');
INSERT INTO `address_areas` VALUES (565, '211382', '凌源市', '211300');
INSERT INTO `address_areas` VALUES (566, '211401', '市辖区', '211400');
INSERT INTO `address_areas` VALUES (567, '211402', '连山区', '211400');
INSERT INTO `address_areas` VALUES (568, '211403', '龙港区', '211400');
INSERT INTO `address_areas` VALUES (569, '211404', '南票区', '211400');
INSERT INTO `address_areas` VALUES (570, '211421', '绥中县', '211400');
INSERT INTO `address_areas` VALUES (571, '211422', '建昌县', '211400');
INSERT INTO `address_areas` VALUES (572, '211481', '兴城市', '211400');
INSERT INTO `address_areas` VALUES (573, '220101', '市辖区', '220100');
INSERT INTO `address_areas` VALUES (574, '220102', '南关区', '220100');
INSERT INTO `address_areas` VALUES (575, '220103', '宽城区', '220100');
INSERT INTO `address_areas` VALUES (576, '220104', '朝阳区', '220100');
INSERT INTO `address_areas` VALUES (577, '220105', '二道区', '220100');
INSERT INTO `address_areas` VALUES (578, '220106', '绿园区', '220100');
INSERT INTO `address_areas` VALUES (579, '220112', '双阳区', '220100');
INSERT INTO `address_areas` VALUES (580, '220122', '农安县', '220100');
INSERT INTO `address_areas` VALUES (581, '220181', '九台市', '220100');
INSERT INTO `address_areas` VALUES (582, '220182', '榆树市', '220100');
INSERT INTO `address_areas` VALUES (583, '220183', '德惠市', '220100');
INSERT INTO `address_areas` VALUES (584, '220201', '市辖区', '220200');
INSERT INTO `address_areas` VALUES (585, '220202', '昌邑区', '220200');
INSERT INTO `address_areas` VALUES (586, '220203', '龙潭区', '220200');
INSERT INTO `address_areas` VALUES (587, '220204', '船营区', '220200');
INSERT INTO `address_areas` VALUES (588, '220211', '丰满区', '220200');
INSERT INTO `address_areas` VALUES (589, '220221', '永吉县', '220200');
INSERT INTO `address_areas` VALUES (590, '220281', '蛟河市', '220200');
INSERT INTO `address_areas` VALUES (591, '220282', '桦甸市', '220200');
INSERT INTO `address_areas` VALUES (592, '220283', '舒兰市', '220200');
INSERT INTO `address_areas` VALUES (593, '220284', '磐石市', '220200');
INSERT INTO `address_areas` VALUES (594, '220301', '市辖区', '220300');
INSERT INTO `address_areas` VALUES (595, '220302', '铁西区', '220300');
INSERT INTO `address_areas` VALUES (596, '220303', '铁东区', '220300');
INSERT INTO `address_areas` VALUES (597, '220322', '梨树县', '220300');
INSERT INTO `address_areas` VALUES (598, '220323', '伊通满族自治县', '220300');
INSERT INTO `address_areas` VALUES (599, '220381', '公主岭市', '220300');
INSERT INTO `address_areas` VALUES (600, '220382', '双辽市', '220300');
INSERT INTO `address_areas` VALUES (601, '220401', '市辖区', '220400');
INSERT INTO `address_areas` VALUES (602, '220402', '龙山区', '220400');
INSERT INTO `address_areas` VALUES (603, '220403', '西安区', '220400');
INSERT INTO `address_areas` VALUES (604, '220421', '东丰县', '220400');
INSERT INTO `address_areas` VALUES (605, '220422', '东辽县', '220400');
INSERT INTO `address_areas` VALUES (606, '220501', '市辖区', '220500');
INSERT INTO `address_areas` VALUES (607, '220502', '东昌区', '220500');
INSERT INTO `address_areas` VALUES (608, '220503', '二道江区', '220500');
INSERT INTO `address_areas` VALUES (609, '220521', '通化县', '220500');
INSERT INTO `address_areas` VALUES (610, '220523', '辉南县', '220500');
INSERT INTO `address_areas` VALUES (611, '220524', '柳河县', '220500');
INSERT INTO `address_areas` VALUES (612, '220581', '梅河口市', '220500');
INSERT INTO `address_areas` VALUES (613, '220582', '集安市', '220500');
INSERT INTO `address_areas` VALUES (614, '220601', '市辖区', '220600');
INSERT INTO `address_areas` VALUES (615, '220602', '八道江区', '220600');
INSERT INTO `address_areas` VALUES (616, '220621', '抚松县', '220600');
INSERT INTO `address_areas` VALUES (617, '220622', '靖宇县', '220600');
INSERT INTO `address_areas` VALUES (618, '220623', '长白朝鲜族自治县', '220600');
INSERT INTO `address_areas` VALUES (619, '220625', '江源县', '220600');
INSERT INTO `address_areas` VALUES (620, '220681', '临江市', '220600');
INSERT INTO `address_areas` VALUES (621, '220701', '市辖区', '220700');
INSERT INTO `address_areas` VALUES (622, '220702', '宁江区', '220700');
INSERT INTO `address_areas` VALUES (623, '220721', '前郭尔罗斯蒙古族自治县', '220700');
INSERT INTO `address_areas` VALUES (624, '220722', '长岭县', '220700');
INSERT INTO `address_areas` VALUES (625, '220723', '乾安县', '220700');
INSERT INTO `address_areas` VALUES (626, '220724', '扶余县', '220700');
INSERT INTO `address_areas` VALUES (627, '220801', '市辖区', '220800');
INSERT INTO `address_areas` VALUES (628, '220802', '洮北区', '220800');
INSERT INTO `address_areas` VALUES (629, '220821', '镇赉县', '220800');
INSERT INTO `address_areas` VALUES (630, '220822', '通榆县', '220800');
INSERT INTO `address_areas` VALUES (631, '220881', '洮南市', '220800');
INSERT INTO `address_areas` VALUES (632, '220882', '大安市', '220800');
INSERT INTO `address_areas` VALUES (633, '222401', '延吉市', '222400');
INSERT INTO `address_areas` VALUES (634, '222402', '图们市', '222400');
INSERT INTO `address_areas` VALUES (635, '222403', '敦化市', '222400');
INSERT INTO `address_areas` VALUES (636, '222404', '珲春市', '222400');
INSERT INTO `address_areas` VALUES (637, '222405', '龙井市', '222400');
INSERT INTO `address_areas` VALUES (638, '222406', '和龙市', '222400');
INSERT INTO `address_areas` VALUES (639, '222424', '汪清县', '222400');
INSERT INTO `address_areas` VALUES (640, '222426', '安图县', '222400');
INSERT INTO `address_areas` VALUES (641, '230101', '市辖区', '230100');
INSERT INTO `address_areas` VALUES (642, '230102', '道里区', '230100');
INSERT INTO `address_areas` VALUES (643, '230103', '南岗区', '230100');
INSERT INTO `address_areas` VALUES (644, '230104', '道外区', '230100');
INSERT INTO `address_areas` VALUES (645, '230106', '香坊区', '230100');
INSERT INTO `address_areas` VALUES (646, '230107', '动力区', '230100');
INSERT INTO `address_areas` VALUES (647, '230108', '平房区', '230100');
INSERT INTO `address_areas` VALUES (648, '230109', '松北区', '230100');
INSERT INTO `address_areas` VALUES (649, '230111', '呼兰区', '230100');
INSERT INTO `address_areas` VALUES (650, '230123', '依兰县', '230100');
INSERT INTO `address_areas` VALUES (651, '230124', '方正县', '230100');
INSERT INTO `address_areas` VALUES (652, '230125', '宾　县', '230100');
INSERT INTO `address_areas` VALUES (653, '230126', '巴彦县', '230100');
INSERT INTO `address_areas` VALUES (654, '230127', '木兰县', '230100');
INSERT INTO `address_areas` VALUES (655, '230128', '通河县', '230100');
INSERT INTO `address_areas` VALUES (656, '230129', '延寿县', '230100');
INSERT INTO `address_areas` VALUES (657, '230181', '阿城市', '230100');
INSERT INTO `address_areas` VALUES (658, '230182', '双城市', '230100');
INSERT INTO `address_areas` VALUES (659, '230183', '尚志市', '230100');
INSERT INTO `address_areas` VALUES (660, '230184', '五常市', '230100');
INSERT INTO `address_areas` VALUES (661, '230201', '市辖区', '230200');
INSERT INTO `address_areas` VALUES (662, '230202', '龙沙区', '230200');
INSERT INTO `address_areas` VALUES (663, '230203', '建华区', '230200');
INSERT INTO `address_areas` VALUES (664, '230204', '铁锋区', '230200');
INSERT INTO `address_areas` VALUES (665, '230205', '昂昂溪区', '230200');
INSERT INTO `address_areas` VALUES (666, '230206', '富拉尔基区', '230200');
INSERT INTO `address_areas` VALUES (667, '230207', '碾子山区', '230200');
INSERT INTO `address_areas` VALUES (668, '230208', '梅里斯达斡尔族区', '230200');
INSERT INTO `address_areas` VALUES (669, '230221', '龙江县', '230200');
INSERT INTO `address_areas` VALUES (670, '230223', '依安县', '230200');
INSERT INTO `address_areas` VALUES (671, '230224', '泰来县', '230200');
INSERT INTO `address_areas` VALUES (672, '230225', '甘南县', '230200');
INSERT INTO `address_areas` VALUES (673, '230227', '富裕县', '230200');
INSERT INTO `address_areas` VALUES (674, '230229', '克山县', '230200');
INSERT INTO `address_areas` VALUES (675, '230230', '克东县', '230200');
INSERT INTO `address_areas` VALUES (676, '230231', '拜泉县', '230200');
INSERT INTO `address_areas` VALUES (677, '230281', '讷河市', '230200');
INSERT INTO `address_areas` VALUES (678, '230301', '市辖区', '230300');
INSERT INTO `address_areas` VALUES (679, '230302', '鸡冠区', '230300');
INSERT INTO `address_areas` VALUES (680, '230303', '恒山区', '230300');
INSERT INTO `address_areas` VALUES (681, '230304', '滴道区', '230300');
INSERT INTO `address_areas` VALUES (682, '230305', '梨树区', '230300');
INSERT INTO `address_areas` VALUES (683, '230306', '城子河区', '230300');
INSERT INTO `address_areas` VALUES (684, '230307', '麻山区', '230300');
INSERT INTO `address_areas` VALUES (685, '230321', '鸡东县', '230300');
INSERT INTO `address_areas` VALUES (686, '230381', '虎林市', '230300');
INSERT INTO `address_areas` VALUES (687, '230382', '密山市', '230300');
INSERT INTO `address_areas` VALUES (688, '230401', '市辖区', '230400');
INSERT INTO `address_areas` VALUES (689, '230402', '向阳区', '230400');
INSERT INTO `address_areas` VALUES (690, '230403', '工农区', '230400');
INSERT INTO `address_areas` VALUES (691, '230404', '南山区', '230400');
INSERT INTO `address_areas` VALUES (692, '230405', '兴安区', '230400');
INSERT INTO `address_areas` VALUES (693, '230406', '东山区', '230400');
INSERT INTO `address_areas` VALUES (694, '230407', '兴山区', '230400');
INSERT INTO `address_areas` VALUES (695, '230421', '萝北县', '230400');
INSERT INTO `address_areas` VALUES (696, '230422', '绥滨县', '230400');
INSERT INTO `address_areas` VALUES (697, '230501', '市辖区', '230500');
INSERT INTO `address_areas` VALUES (698, '230502', '尖山区', '230500');
INSERT INTO `address_areas` VALUES (699, '230503', '岭东区', '230500');
INSERT INTO `address_areas` VALUES (700, '230505', '四方台区', '230500');
INSERT INTO `address_areas` VALUES (701, '230506', '宝山区', '230500');
INSERT INTO `address_areas` VALUES (702, '230521', '集贤县', '230500');
INSERT INTO `address_areas` VALUES (703, '230522', '友谊县', '230500');
INSERT INTO `address_areas` VALUES (704, '230523', '宝清县', '230500');
INSERT INTO `address_areas` VALUES (705, '230524', '饶河县', '230500');
INSERT INTO `address_areas` VALUES (706, '230601', '市辖区', '230600');
INSERT INTO `address_areas` VALUES (707, '230602', '萨尔图区', '230600');
INSERT INTO `address_areas` VALUES (708, '230603', '龙凤区', '230600');
INSERT INTO `address_areas` VALUES (709, '230604', '让胡路区', '230600');
INSERT INTO `address_areas` VALUES (710, '230605', '红岗区', '230600');
INSERT INTO `address_areas` VALUES (711, '230606', '大同区', '230600');
INSERT INTO `address_areas` VALUES (712, '230621', '肇州县', '230600');
INSERT INTO `address_areas` VALUES (713, '230622', '肇源县', '230600');
INSERT INTO `address_areas` VALUES (714, '230623', '林甸县', '230600');
INSERT INTO `address_areas` VALUES (715, '230624', '杜尔伯特蒙古族自治县', '230600');
INSERT INTO `address_areas` VALUES (716, '230701', '市辖区', '230700');
INSERT INTO `address_areas` VALUES (717, '230702', '伊春区', '230700');
INSERT INTO `address_areas` VALUES (718, '230703', '南岔区', '230700');
INSERT INTO `address_areas` VALUES (719, '230704', '友好区', '230700');
INSERT INTO `address_areas` VALUES (720, '230705', '西林区', '230700');
INSERT INTO `address_areas` VALUES (721, '230706', '翠峦区', '230700');
INSERT INTO `address_areas` VALUES (722, '230707', '新青区', '230700');
INSERT INTO `address_areas` VALUES (723, '230708', '美溪区', '230700');
INSERT INTO `address_areas` VALUES (724, '230709', '金山屯区', '230700');
INSERT INTO `address_areas` VALUES (725, '230710', '五营区', '230700');
INSERT INTO `address_areas` VALUES (726, '230711', '乌马河区', '230700');
INSERT INTO `address_areas` VALUES (727, '230712', '汤旺河区', '230700');
INSERT INTO `address_areas` VALUES (728, '230713', '带岭区', '230700');
INSERT INTO `address_areas` VALUES (729, '230714', '乌伊岭区', '230700');
INSERT INTO `address_areas` VALUES (730, '230715', '红星区', '230700');
INSERT INTO `address_areas` VALUES (731, '230716', '上甘岭区', '230700');
INSERT INTO `address_areas` VALUES (732, '230722', '嘉荫县', '230700');
INSERT INTO `address_areas` VALUES (733, '230781', '铁力市', '230700');
INSERT INTO `address_areas` VALUES (734, '230801', '市辖区', '230800');
INSERT INTO `address_areas` VALUES (735, '230802', '永红区', '230800');
INSERT INTO `address_areas` VALUES (736, '230803', '向阳区', '230800');
INSERT INTO `address_areas` VALUES (737, '230804', '前进区', '230800');
INSERT INTO `address_areas` VALUES (738, '230805', '东风区', '230800');
INSERT INTO `address_areas` VALUES (739, '230811', '郊　区', '230800');
INSERT INTO `address_areas` VALUES (740, '230822', '桦南县', '230800');
INSERT INTO `address_areas` VALUES (741, '230826', '桦川县', '230800');
INSERT INTO `address_areas` VALUES (742, '230828', '汤原县', '230800');
INSERT INTO `address_areas` VALUES (743, '230833', '抚远县', '230800');
INSERT INTO `address_areas` VALUES (744, '230881', '同江市', '230800');
INSERT INTO `address_areas` VALUES (745, '230882', '富锦市', '230800');
INSERT INTO `address_areas` VALUES (746, '230901', '市辖区', '230900');
INSERT INTO `address_areas` VALUES (747, '230902', '新兴区', '230900');
INSERT INTO `address_areas` VALUES (748, '230903', '桃山区', '230900');
INSERT INTO `address_areas` VALUES (749, '230904', '茄子河区', '230900');
INSERT INTO `address_areas` VALUES (750, '230921', '勃利县', '230900');
INSERT INTO `address_areas` VALUES (751, '231001', '市辖区', '231000');
INSERT INTO `address_areas` VALUES (752, '231002', '东安区', '231000');
INSERT INTO `address_areas` VALUES (753, '231003', '阳明区', '231000');
INSERT INTO `address_areas` VALUES (754, '231004', '爱民区', '231000');
INSERT INTO `address_areas` VALUES (755, '231005', '西安区', '231000');
INSERT INTO `address_areas` VALUES (756, '231024', '东宁县', '231000');
INSERT INTO `address_areas` VALUES (757, '231025', '林口县', '231000');
INSERT INTO `address_areas` VALUES (758, '231081', '绥芬河市', '231000');
INSERT INTO `address_areas` VALUES (759, '231083', '海林市', '231000');
INSERT INTO `address_areas` VALUES (760, '231084', '宁安市', '231000');
INSERT INTO `address_areas` VALUES (761, '231085', '穆棱市', '231000');
INSERT INTO `address_areas` VALUES (762, '231101', '市辖区', '231100');
INSERT INTO `address_areas` VALUES (763, '231102', '爱辉区', '231100');
INSERT INTO `address_areas` VALUES (764, '231121', '嫩江县', '231100');
INSERT INTO `address_areas` VALUES (765, '231123', '逊克县', '231100');
INSERT INTO `address_areas` VALUES (766, '231124', '孙吴县', '231100');
INSERT INTO `address_areas` VALUES (767, '231181', '北安市', '231100');
INSERT INTO `address_areas` VALUES (768, '231182', '五大连池市', '231100');
INSERT INTO `address_areas` VALUES (769, '231201', '市辖区', '231200');
INSERT INTO `address_areas` VALUES (770, '231202', '北林区', '231200');
INSERT INTO `address_areas` VALUES (771, '231221', '望奎县', '231200');
INSERT INTO `address_areas` VALUES (772, '231222', '兰西县', '231200');
INSERT INTO `address_areas` VALUES (773, '231223', '青冈县', '231200');
INSERT INTO `address_areas` VALUES (774, '231224', '庆安县', '231200');
INSERT INTO `address_areas` VALUES (775, '231225', '明水县', '231200');
INSERT INTO `address_areas` VALUES (776, '231226', '绥棱县', '231200');
INSERT INTO `address_areas` VALUES (777, '231281', '安达市', '231200');
INSERT INTO `address_areas` VALUES (778, '231282', '肇东市', '231200');
INSERT INTO `address_areas` VALUES (779, '231283', '海伦市', '231200');
INSERT INTO `address_areas` VALUES (780, '232721', '呼玛县', '232700');
INSERT INTO `address_areas` VALUES (781, '232722', '塔河县', '232700');
INSERT INTO `address_areas` VALUES (782, '232723', '漠河县', '232700');
INSERT INTO `address_areas` VALUES (783, '310101', '黄浦区', '310100');
INSERT INTO `address_areas` VALUES (784, '310103', '卢湾区', '310100');
INSERT INTO `address_areas` VALUES (785, '310104', '徐汇区', '310100');
INSERT INTO `address_areas` VALUES (786, '310105', '长宁区', '310100');
INSERT INTO `address_areas` VALUES (787, '310106', '静安区', '310100');
INSERT INTO `address_areas` VALUES (788, '310107', '普陀区', '310100');
INSERT INTO `address_areas` VALUES (789, '310108', '闸北区', '310100');
INSERT INTO `address_areas` VALUES (790, '310109', '虹口区', '310100');
INSERT INTO `address_areas` VALUES (791, '310110', '杨浦区', '310100');
INSERT INTO `address_areas` VALUES (792, '310112', '闵行区', '310100');
INSERT INTO `address_areas` VALUES (793, '310113', '宝山区', '310100');
INSERT INTO `address_areas` VALUES (794, '310114', '嘉定区', '310100');
INSERT INTO `address_areas` VALUES (795, '310115', '浦东新区', '310100');
INSERT INTO `address_areas` VALUES (796, '310116', '金山区', '310100');
INSERT INTO `address_areas` VALUES (797, '310117', '松江区', '310100');
INSERT INTO `address_areas` VALUES (798, '310118', '青浦区', '310100');
INSERT INTO `address_areas` VALUES (799, '310119', '南汇区', '310100');
INSERT INTO `address_areas` VALUES (800, '310120', '奉贤区', '310100');
INSERT INTO `address_areas` VALUES (801, '310230', '崇明县', '310100');
INSERT INTO `address_areas` VALUES (802, '320101', '市辖区', '320100');
INSERT INTO `address_areas` VALUES (803, '320102', '玄武区', '320100');
INSERT INTO `address_areas` VALUES (804, '320103', '白下区', '320100');
INSERT INTO `address_areas` VALUES (805, '320104', '秦淮区', '320100');
INSERT INTO `address_areas` VALUES (806, '320105', '建邺区', '320100');
INSERT INTO `address_areas` VALUES (807, '320106', '鼓楼区', '320100');
INSERT INTO `address_areas` VALUES (808, '320107', '下关区', '320100');
INSERT INTO `address_areas` VALUES (809, '320111', '浦口区', '320100');
INSERT INTO `address_areas` VALUES (810, '320113', '栖霞区', '320100');
INSERT INTO `address_areas` VALUES (811, '320114', '雨花台区', '320100');
INSERT INTO `address_areas` VALUES (812, '320115', '江宁区', '320100');
INSERT INTO `address_areas` VALUES (813, '320116', '六合区', '320100');
INSERT INTO `address_areas` VALUES (814, '320124', '溧水县', '320100');
INSERT INTO `address_areas` VALUES (815, '320125', '高淳县', '320100');
INSERT INTO `address_areas` VALUES (816, '320201', '市辖区', '320200');
INSERT INTO `address_areas` VALUES (817, '320202', '崇安区', '320200');
INSERT INTO `address_areas` VALUES (818, '320203', '南长区', '320200');
INSERT INTO `address_areas` VALUES (819, '320204', '北塘区', '320200');
INSERT INTO `address_areas` VALUES (820, '320205', '锡山区', '320200');
INSERT INTO `address_areas` VALUES (821, '320206', '惠山区', '320200');
INSERT INTO `address_areas` VALUES (822, '320211', '滨湖区', '320200');
INSERT INTO `address_areas` VALUES (823, '320281', '江阴市', '320200');
INSERT INTO `address_areas` VALUES (824, '320282', '宜兴市', '320200');
INSERT INTO `address_areas` VALUES (825, '320301', '市辖区', '320300');
INSERT INTO `address_areas` VALUES (826, '320302', '鼓楼区', '320300');
INSERT INTO `address_areas` VALUES (827, '320303', '云龙区', '320300');
INSERT INTO `address_areas` VALUES (828, '320304', '九里区', '320300');
INSERT INTO `address_areas` VALUES (829, '320305', '贾汪区', '320300');
INSERT INTO `address_areas` VALUES (830, '320311', '泉山区', '320300');
INSERT INTO `address_areas` VALUES (831, '320321', '丰　县', '320300');
INSERT INTO `address_areas` VALUES (832, '320322', '沛　县', '320300');
INSERT INTO `address_areas` VALUES (833, '320323', '铜山县', '320300');
INSERT INTO `address_areas` VALUES (834, '320324', '睢宁县', '320300');
INSERT INTO `address_areas` VALUES (835, '320381', '新沂市', '320300');
INSERT INTO `address_areas` VALUES (836, '320382', '邳州市', '320300');
INSERT INTO `address_areas` VALUES (837, '320401', '市辖区', '320400');
INSERT INTO `address_areas` VALUES (838, '320402', '天宁区', '320400');
INSERT INTO `address_areas` VALUES (839, '320404', '钟楼区', '320400');
INSERT INTO `address_areas` VALUES (840, '320405', '戚墅堰区', '320400');
INSERT INTO `address_areas` VALUES (841, '320411', '新北区', '320400');
INSERT INTO `address_areas` VALUES (842, '320412', '武进区', '320400');
INSERT INTO `address_areas` VALUES (843, '320481', '溧阳市', '320400');
INSERT INTO `address_areas` VALUES (844, '320482', '金坛市', '320400');
INSERT INTO `address_areas` VALUES (845, '320501', '市辖区', '320500');
INSERT INTO `address_areas` VALUES (846, '320502', '沧浪区', '320500');
INSERT INTO `address_areas` VALUES (847, '320503', '平江区', '320500');
INSERT INTO `address_areas` VALUES (848, '320504', '金阊区', '320500');
INSERT INTO `address_areas` VALUES (849, '320505', '虎丘区', '320500');
INSERT INTO `address_areas` VALUES (850, '320506', '吴中区', '320500');
INSERT INTO `address_areas` VALUES (851, '320507', '相城区', '320500');
INSERT INTO `address_areas` VALUES (852, '320581', '常熟市', '320500');
INSERT INTO `address_areas` VALUES (853, '320582', '张家港市', '320500');
INSERT INTO `address_areas` VALUES (854, '320583', '昆山市', '320500');
INSERT INTO `address_areas` VALUES (855, '320584', '吴江市', '320500');
INSERT INTO `address_areas` VALUES (856, '320585', '太仓市', '320500');
INSERT INTO `address_areas` VALUES (857, '320601', '市辖区', '320600');
INSERT INTO `address_areas` VALUES (858, '320602', '崇川区', '320600');
INSERT INTO `address_areas` VALUES (859, '320611', '港闸区', '320600');
INSERT INTO `address_areas` VALUES (860, '320621', '海安县', '320600');
INSERT INTO `address_areas` VALUES (861, '320623', '如东县', '320600');
INSERT INTO `address_areas` VALUES (862, '320681', '启东市', '320600');
INSERT INTO `address_areas` VALUES (863, '320682', '如皋市', '320600');
INSERT INTO `address_areas` VALUES (864, '320683', '通州市', '320600');
INSERT INTO `address_areas` VALUES (865, '320684', '海门市', '320600');
INSERT INTO `address_areas` VALUES (866, '320701', '市辖区', '320700');
INSERT INTO `address_areas` VALUES (867, '320703', '连云区', '320700');
INSERT INTO `address_areas` VALUES (868, '320705', '新浦区', '320700');
INSERT INTO `address_areas` VALUES (869, '320706', '海州区', '320700');
INSERT INTO `address_areas` VALUES (870, '320721', '赣榆县', '320700');
INSERT INTO `address_areas` VALUES (871, '320722', '东海县', '320700');
INSERT INTO `address_areas` VALUES (872, '320723', '灌云县', '320700');
INSERT INTO `address_areas` VALUES (873, '320724', '灌南县', '320700');
INSERT INTO `address_areas` VALUES (874, '320801', '市辖区', '320800');
INSERT INTO `address_areas` VALUES (875, '320802', '清河区', '320800');
INSERT INTO `address_areas` VALUES (876, '320803', '楚州区', '320800');
INSERT INTO `address_areas` VALUES (877, '320804', '淮阴区', '320800');
INSERT INTO `address_areas` VALUES (878, '320811', '清浦区', '320800');
INSERT INTO `address_areas` VALUES (879, '320826', '涟水县', '320800');
INSERT INTO `address_areas` VALUES (880, '320829', '洪泽县', '320800');
INSERT INTO `address_areas` VALUES (881, '320830', '盱眙县', '320800');
INSERT INTO `address_areas` VALUES (882, '320831', '金湖县', '320800');
INSERT INTO `address_areas` VALUES (883, '320901', '市辖区', '320900');
INSERT INTO `address_areas` VALUES (884, '320902', '亭湖区', '320900');
INSERT INTO `address_areas` VALUES (885, '320903', '盐都区', '320900');
INSERT INTO `address_areas` VALUES (886, '320921', '响水县', '320900');
INSERT INTO `address_areas` VALUES (887, '320922', '滨海县', '320900');
INSERT INTO `address_areas` VALUES (888, '320923', '阜宁县', '320900');
INSERT INTO `address_areas` VALUES (889, '320924', '射阳县', '320900');
INSERT INTO `address_areas` VALUES (890, '320925', '建湖县', '320900');
INSERT INTO `address_areas` VALUES (891, '320981', '东台市', '320900');
INSERT INTO `address_areas` VALUES (892, '320982', '大丰市', '320900');
INSERT INTO `address_areas` VALUES (893, '321001', '市辖区', '321000');
INSERT INTO `address_areas` VALUES (894, '321002', '广陵区', '321000');
INSERT INTO `address_areas` VALUES (895, '321003', '邗江区', '321000');
INSERT INTO `address_areas` VALUES (896, '321011', '郊　区', '321000');
INSERT INTO `address_areas` VALUES (897, '321023', '宝应县', '321000');
INSERT INTO `address_areas` VALUES (898, '321081', '仪征市', '321000');
INSERT INTO `address_areas` VALUES (899, '321084', '高邮市', '321000');
INSERT INTO `address_areas` VALUES (900, '321088', '江都市', '321000');
INSERT INTO `address_areas` VALUES (901, '321101', '市辖区', '321100');
INSERT INTO `address_areas` VALUES (902, '321102', '京口区', '321100');
INSERT INTO `address_areas` VALUES (903, '321111', '润州区', '321100');
INSERT INTO `address_areas` VALUES (904, '321112', '丹徒区', '321100');
INSERT INTO `address_areas` VALUES (905, '321181', '丹阳市', '321100');
INSERT INTO `address_areas` VALUES (906, '321182', '扬中市', '321100');
INSERT INTO `address_areas` VALUES (907, '321183', '句容市', '321100');
INSERT INTO `address_areas` VALUES (908, '321201', '市辖区', '321200');
INSERT INTO `address_areas` VALUES (909, '321202', '海陵区', '321200');
INSERT INTO `address_areas` VALUES (910, '321203', '高港区', '321200');
INSERT INTO `address_areas` VALUES (911, '321281', '兴化市', '321200');
INSERT INTO `address_areas` VALUES (912, '321282', '靖江市', '321200');
INSERT INTO `address_areas` VALUES (913, '321283', '泰兴市', '321200');
INSERT INTO `address_areas` VALUES (914, '321284', '姜堰市', '321200');
INSERT INTO `address_areas` VALUES (915, '321301', '市辖区', '321300');
INSERT INTO `address_areas` VALUES (916, '321302', '宿城区', '321300');
INSERT INTO `address_areas` VALUES (917, '321311', '宿豫区', '321300');
INSERT INTO `address_areas` VALUES (918, '321322', '沭阳县', '321300');
INSERT INTO `address_areas` VALUES (919, '321323', '泗阳县', '321300');
INSERT INTO `address_areas` VALUES (920, '321324', '泗洪县', '321300');
INSERT INTO `address_areas` VALUES (921, '330101', '市辖区', '330100');
INSERT INTO `address_areas` VALUES (922, '330102', '上城区', '330100');
INSERT INTO `address_areas` VALUES (923, '330103', '下城区', '330100');
INSERT INTO `address_areas` VALUES (924, '330104', '江干区', '330100');
INSERT INTO `address_areas` VALUES (925, '330105', '拱墅区', '330100');
INSERT INTO `address_areas` VALUES (926, '330106', '西湖区', '330100');
INSERT INTO `address_areas` VALUES (927, '330108', '滨江区', '330100');
INSERT INTO `address_areas` VALUES (928, '330109', '萧山区', '330100');
INSERT INTO `address_areas` VALUES (929, '330110', '余杭区', '330100');
INSERT INTO `address_areas` VALUES (930, '330122', '桐庐县', '330100');
INSERT INTO `address_areas` VALUES (931, '330127', '淳安县', '330100');
INSERT INTO `address_areas` VALUES (932, '330182', '建德市', '330100');
INSERT INTO `address_areas` VALUES (933, '330183', '富阳市', '330100');
INSERT INTO `address_areas` VALUES (934, '330185', '临安市', '330100');
INSERT INTO `address_areas` VALUES (935, '330201', '市辖区', '330200');
INSERT INTO `address_areas` VALUES (936, '330203', '海曙区', '330200');
INSERT INTO `address_areas` VALUES (937, '330204', '江东区', '330200');
INSERT INTO `address_areas` VALUES (938, '330205', '江北区', '330200');
INSERT INTO `address_areas` VALUES (939, '330206', '北仑区', '330200');
INSERT INTO `address_areas` VALUES (940, '330211', '镇海区', '330200');
INSERT INTO `address_areas` VALUES (941, '330212', '鄞州区', '330200');
INSERT INTO `address_areas` VALUES (942, '330225', '象山县', '330200');
INSERT INTO `address_areas` VALUES (943, '330226', '宁海县', '330200');
INSERT INTO `address_areas` VALUES (944, '330281', '余姚市', '330200');
INSERT INTO `address_areas` VALUES (945, '330282', '慈溪市', '330200');
INSERT INTO `address_areas` VALUES (946, '330283', '奉化市', '330200');
INSERT INTO `address_areas` VALUES (947, '330301', '市辖区', '330300');
INSERT INTO `address_areas` VALUES (948, '330302', '鹿城区', '330300');
INSERT INTO `address_areas` VALUES (949, '330303', '龙湾区', '330300');
INSERT INTO `address_areas` VALUES (950, '330304', '瓯海区', '330300');
INSERT INTO `address_areas` VALUES (951, '330322', '洞头县', '330300');
INSERT INTO `address_areas` VALUES (952, '330324', '永嘉县', '330300');
INSERT INTO `address_areas` VALUES (953, '330326', '平阳县', '330300');
INSERT INTO `address_areas` VALUES (954, '330327', '苍南县', '330300');
INSERT INTO `address_areas` VALUES (955, '330328', '文成县', '330300');
INSERT INTO `address_areas` VALUES (956, '330329', '泰顺县', '330300');
INSERT INTO `address_areas` VALUES (957, '330381', '瑞安市', '330300');
INSERT INTO `address_areas` VALUES (958, '330382', '乐清市', '330300');
INSERT INTO `address_areas` VALUES (959, '330401', '市辖区', '330400');
INSERT INTO `address_areas` VALUES (960, '330402', '秀城区', '330400');
INSERT INTO `address_areas` VALUES (961, '330411', '秀洲区', '330400');
INSERT INTO `address_areas` VALUES (962, '330421', '嘉善县', '330400');
INSERT INTO `address_areas` VALUES (963, '330424', '海盐县', '330400');
INSERT INTO `address_areas` VALUES (964, '330481', '海宁市', '330400');
INSERT INTO `address_areas` VALUES (965, '330482', '平湖市', '330400');
INSERT INTO `address_areas` VALUES (966, '330483', '桐乡市', '330400');
INSERT INTO `address_areas` VALUES (967, '330501', '市辖区', '330500');
INSERT INTO `address_areas` VALUES (968, '330502', '吴兴区', '330500');
INSERT INTO `address_areas` VALUES (969, '330503', '南浔区', '330500');
INSERT INTO `address_areas` VALUES (970, '330521', '德清县', '330500');
INSERT INTO `address_areas` VALUES (971, '330522', '长兴县', '330500');
INSERT INTO `address_areas` VALUES (972, '330523', '安吉县', '330500');
INSERT INTO `address_areas` VALUES (973, '330601', '市辖区', '330600');
INSERT INTO `address_areas` VALUES (974, '330602', '越城区', '330600');
INSERT INTO `address_areas` VALUES (975, '330621', '绍兴县', '330600');
INSERT INTO `address_areas` VALUES (976, '330624', '新昌县', '330600');
INSERT INTO `address_areas` VALUES (977, '330681', '诸暨市', '330600');
INSERT INTO `address_areas` VALUES (978, '330682', '上虞市', '330600');
INSERT INTO `address_areas` VALUES (979, '330683', '嵊州市', '330600');
INSERT INTO `address_areas` VALUES (980, '330701', '市辖区', '330700');
INSERT INTO `address_areas` VALUES (981, '330702', '婺城区', '330700');
INSERT INTO `address_areas` VALUES (982, '330703', '金东区', '330700');
INSERT INTO `address_areas` VALUES (983, '330723', '武义县', '330700');
INSERT INTO `address_areas` VALUES (984, '330726', '浦江县', '330700');
INSERT INTO `address_areas` VALUES (985, '330727', '磐安县', '330700');
INSERT INTO `address_areas` VALUES (986, '330781', '兰溪市', '330700');
INSERT INTO `address_areas` VALUES (987, '330782', '义乌市', '330700');
INSERT INTO `address_areas` VALUES (988, '330783', '东阳市', '330700');
INSERT INTO `address_areas` VALUES (989, '330784', '永康市', '330700');
INSERT INTO `address_areas` VALUES (990, '330801', '市辖区', '330800');
INSERT INTO `address_areas` VALUES (991, '330802', '柯城区', '330800');
INSERT INTO `address_areas` VALUES (992, '330803', '衢江区', '330800');
INSERT INTO `address_areas` VALUES (993, '330822', '常山县', '330800');
INSERT INTO `address_areas` VALUES (994, '330824', '开化县', '330800');
INSERT INTO `address_areas` VALUES (995, '330825', '龙游县', '330800');
INSERT INTO `address_areas` VALUES (996, '330881', '江山市', '330800');
INSERT INTO `address_areas` VALUES (997, '330901', '市辖区', '330900');
INSERT INTO `address_areas` VALUES (998, '330902', '定海区', '330900');
INSERT INTO `address_areas` VALUES (999, '330903', '普陀区', '330900');
INSERT INTO `address_areas` VALUES (1000, '330921', '岱山县', '330900');
INSERT INTO `address_areas` VALUES (1001, '330922', '嵊泗县', '330900');
INSERT INTO `address_areas` VALUES (1002, '331001', '市辖区', '331000');
INSERT INTO `address_areas` VALUES (1003, '331002', '椒江区', '331000');
INSERT INTO `address_areas` VALUES (1004, '331003', '黄岩区', '331000');
INSERT INTO `address_areas` VALUES (1005, '331004', '路桥区', '331000');
INSERT INTO `address_areas` VALUES (1006, '331021', '玉环县', '331000');
INSERT INTO `address_areas` VALUES (1007, '331022', '三门县', '331000');
INSERT INTO `address_areas` VALUES (1008, '331023', '天台县', '331000');
INSERT INTO `address_areas` VALUES (1009, '331024', '仙居县', '331000');
INSERT INTO `address_areas` VALUES (1010, '331081', '温岭市', '331000');
INSERT INTO `address_areas` VALUES (1011, '331082', '临海市', '331000');
INSERT INTO `address_areas` VALUES (1012, '331101', '市辖区', '331100');
INSERT INTO `address_areas` VALUES (1013, '331102', '莲都区', '331100');
INSERT INTO `address_areas` VALUES (1014, '331121', '青田县', '331100');
INSERT INTO `address_areas` VALUES (1015, '331122', '缙云县', '331100');
INSERT INTO `address_areas` VALUES (1016, '331123', '遂昌县', '331100');
INSERT INTO `address_areas` VALUES (1017, '331124', '松阳县', '331100');
INSERT INTO `address_areas` VALUES (1018, '331125', '云和县', '331100');
INSERT INTO `address_areas` VALUES (1019, '331126', '庆元县', '331100');
INSERT INTO `address_areas` VALUES (1020, '331127', '景宁畲族自治县', '331100');
INSERT INTO `address_areas` VALUES (1021, '331181', '龙泉市', '331100');
INSERT INTO `address_areas` VALUES (1022, '340101', '市辖区', '340100');
INSERT INTO `address_areas` VALUES (1023, '340102', '瑶海区', '340100');
INSERT INTO `address_areas` VALUES (1024, '340103', '庐阳区', '340100');
INSERT INTO `address_areas` VALUES (1025, '340104', '蜀山区', '340100');
INSERT INTO `address_areas` VALUES (1026, '340111', '包河区', '340100');
INSERT INTO `address_areas` VALUES (1027, '340121', '长丰县', '340100');
INSERT INTO `address_areas` VALUES (1028, '340122', '肥东县', '340100');
INSERT INTO `address_areas` VALUES (1029, '340123', '肥西县', '340100');
INSERT INTO `address_areas` VALUES (1030, '340201', '市辖区', '340200');
INSERT INTO `address_areas` VALUES (1031, '340202', '镜湖区', '340200');
INSERT INTO `address_areas` VALUES (1032, '340203', '马塘区', '340200');
INSERT INTO `address_areas` VALUES (1033, '340204', '新芜区', '340200');
INSERT INTO `address_areas` VALUES (1034, '340207', '鸠江区', '340200');
INSERT INTO `address_areas` VALUES (1035, '340221', '芜湖县', '340200');
INSERT INTO `address_areas` VALUES (1036, '340222', '繁昌县', '340200');
INSERT INTO `address_areas` VALUES (1037, '340223', '南陵县', '340200');
INSERT INTO `address_areas` VALUES (1038, '340301', '市辖区', '340300');
INSERT INTO `address_areas` VALUES (1039, '340302', '龙子湖区', '340300');
INSERT INTO `address_areas` VALUES (1040, '340303', '蚌山区', '340300');
INSERT INTO `address_areas` VALUES (1041, '340304', '禹会区', '340300');
INSERT INTO `address_areas` VALUES (1042, '340311', '淮上区', '340300');
INSERT INTO `address_areas` VALUES (1043, '340321', '怀远县', '340300');
INSERT INTO `address_areas` VALUES (1044, '340322', '五河县', '340300');
INSERT INTO `address_areas` VALUES (1045, '340323', '固镇县', '340300');
INSERT INTO `address_areas` VALUES (1046, '340401', '市辖区', '340400');
INSERT INTO `address_areas` VALUES (1047, '340402', '大通区', '340400');
INSERT INTO `address_areas` VALUES (1048, '340403', '田家庵区', '340400');
INSERT INTO `address_areas` VALUES (1049, '340404', '谢家集区', '340400');
INSERT INTO `address_areas` VALUES (1050, '340405', '八公山区', '340400');
INSERT INTO `address_areas` VALUES (1051, '340406', '潘集区', '340400');
INSERT INTO `address_areas` VALUES (1052, '340421', '凤台县', '340400');
INSERT INTO `address_areas` VALUES (1053, '340501', '市辖区', '340500');
INSERT INTO `address_areas` VALUES (1054, '340502', '金家庄区', '340500');
INSERT INTO `address_areas` VALUES (1055, '340503', '花山区', '340500');
INSERT INTO `address_areas` VALUES (1056, '340504', '雨山区', '340500');
INSERT INTO `address_areas` VALUES (1057, '340521', '当涂县', '340500');
INSERT INTO `address_areas` VALUES (1058, '340601', '市辖区', '340600');
INSERT INTO `address_areas` VALUES (1059, '340602', '杜集区', '340600');
INSERT INTO `address_areas` VALUES (1060, '340603', '相山区', '340600');
INSERT INTO `address_areas` VALUES (1061, '340604', '烈山区', '340600');
INSERT INTO `address_areas` VALUES (1062, '340621', '濉溪县', '340600');
INSERT INTO `address_areas` VALUES (1063, '340701', '市辖区', '340700');
INSERT INTO `address_areas` VALUES (1064, '340702', '铜官山区', '340700');
INSERT INTO `address_areas` VALUES (1065, '340703', '狮子山区', '340700');
INSERT INTO `address_areas` VALUES (1066, '340711', '郊　区', '340700');
INSERT INTO `address_areas` VALUES (1067, '340721', '铜陵县', '340700');
INSERT INTO `address_areas` VALUES (1068, '340801', '市辖区', '340800');
INSERT INTO `address_areas` VALUES (1069, '340802', '迎江区', '340800');
INSERT INTO `address_areas` VALUES (1070, '340803', '大观区', '340800');
INSERT INTO `address_areas` VALUES (1071, '340811', '郊　区', '340800');
INSERT INTO `address_areas` VALUES (1072, '340822', '怀宁县', '340800');
INSERT INTO `address_areas` VALUES (1073, '340823', '枞阳县', '340800');
INSERT INTO `address_areas` VALUES (1074, '340824', '潜山县', '340800');
INSERT INTO `address_areas` VALUES (1075, '340825', '太湖县', '340800');
INSERT INTO `address_areas` VALUES (1076, '340826', '宿松县', '340800');
INSERT INTO `address_areas` VALUES (1077, '340827', '望江县', '340800');
INSERT INTO `address_areas` VALUES (1078, '340828', '岳西县', '340800');
INSERT INTO `address_areas` VALUES (1079, '340881', '桐城市', '340800');
INSERT INTO `address_areas` VALUES (1080, '341001', '市辖区', '341000');
INSERT INTO `address_areas` VALUES (1081, '341002', '屯溪区', '341000');
INSERT INTO `address_areas` VALUES (1082, '341003', '黄山区', '341000');
INSERT INTO `address_areas` VALUES (1083, '341004', '徽州区', '341000');
INSERT INTO `address_areas` VALUES (1084, '341021', '歙　县', '341000');
INSERT INTO `address_areas` VALUES (1085, '341022', '休宁县', '341000');
INSERT INTO `address_areas` VALUES (1086, '341023', '黟　县', '341000');
INSERT INTO `address_areas` VALUES (1087, '341024', '祁门县', '341000');
INSERT INTO `address_areas` VALUES (1088, '341101', '市辖区', '341100');
INSERT INTO `address_areas` VALUES (1089, '341102', '琅琊区', '341100');
INSERT INTO `address_areas` VALUES (1090, '341103', '南谯区', '341100');
INSERT INTO `address_areas` VALUES (1091, '341122', '来安县', '341100');
INSERT INTO `address_areas` VALUES (1092, '341124', '全椒县', '341100');
INSERT INTO `address_areas` VALUES (1093, '341125', '定远县', '341100');
INSERT INTO `address_areas` VALUES (1094, '341126', '凤阳县', '341100');
INSERT INTO `address_areas` VALUES (1095, '341181', '天长市', '341100');
INSERT INTO `address_areas` VALUES (1096, '341182', '明光市', '341100');
INSERT INTO `address_areas` VALUES (1097, '341201', '市辖区', '341200');
INSERT INTO `address_areas` VALUES (1098, '341202', '颍州区', '341200');
INSERT INTO `address_areas` VALUES (1099, '341203', '颍东区', '341200');
INSERT INTO `address_areas` VALUES (1100, '341204', '颍泉区', '341200');
INSERT INTO `address_areas` VALUES (1101, '341221', '临泉县', '341200');
INSERT INTO `address_areas` VALUES (1102, '341222', '太和县', '341200');
INSERT INTO `address_areas` VALUES (1103, '341225', '阜南县', '341200');
INSERT INTO `address_areas` VALUES (1104, '341226', '颍上县', '341200');
INSERT INTO `address_areas` VALUES (1105, '341282', '界首市', '341200');
INSERT INTO `address_areas` VALUES (1106, '341301', '市辖区', '341300');
INSERT INTO `address_areas` VALUES (1107, '341302', '墉桥区', '341300');
INSERT INTO `address_areas` VALUES (1108, '341321', '砀山县', '341300');
INSERT INTO `address_areas` VALUES (1109, '341322', '萧　县', '341300');
INSERT INTO `address_areas` VALUES (1110, '341323', '灵璧县', '341300');
INSERT INTO `address_areas` VALUES (1111, '341324', '泗　县', '341300');
INSERT INTO `address_areas` VALUES (1112, '341401', '市辖区', '341400');
INSERT INTO `address_areas` VALUES (1113, '341402', '居巢区', '341400');
INSERT INTO `address_areas` VALUES (1114, '341421', '庐江县', '341400');
INSERT INTO `address_areas` VALUES (1115, '341422', '无为县', '341400');
INSERT INTO `address_areas` VALUES (1116, '341423', '含山县', '341400');
INSERT INTO `address_areas` VALUES (1117, '341424', '和　县', '341400');
INSERT INTO `address_areas` VALUES (1118, '341501', '市辖区', '341500');
INSERT INTO `address_areas` VALUES (1119, '341502', '金安区', '341500');
INSERT INTO `address_areas` VALUES (1120, '341503', '裕安区', '341500');
INSERT INTO `address_areas` VALUES (1121, '341521', '寿　县', '341500');
INSERT INTO `address_areas` VALUES (1122, '341522', '霍邱县', '341500');
INSERT INTO `address_areas` VALUES (1123, '341523', '舒城县', '341500');
INSERT INTO `address_areas` VALUES (1124, '341524', '金寨县', '341500');
INSERT INTO `address_areas` VALUES (1125, '341525', '霍山县', '341500');
INSERT INTO `address_areas` VALUES (1126, '341601', '市辖区', '341600');
INSERT INTO `address_areas` VALUES (1127, '341602', '谯城区', '341600');
INSERT INTO `address_areas` VALUES (1128, '341621', '涡阳县', '341600');
INSERT INTO `address_areas` VALUES (1129, '341622', '蒙城县', '341600');
INSERT INTO `address_areas` VALUES (1130, '341623', '利辛县', '341600');
INSERT INTO `address_areas` VALUES (1131, '341701', '市辖区', '341700');
INSERT INTO `address_areas` VALUES (1132, '341702', '贵池区', '341700');
INSERT INTO `address_areas` VALUES (1133, '341721', '东至县', '341700');
INSERT INTO `address_areas` VALUES (1134, '341722', '石台县', '341700');
INSERT INTO `address_areas` VALUES (1135, '341723', '青阳县', '341700');
INSERT INTO `address_areas` VALUES (1136, '341801', '市辖区', '341800');
INSERT INTO `address_areas` VALUES (1137, '341802', '宣州区', '341800');
INSERT INTO `address_areas` VALUES (1138, '341821', '郎溪县', '341800');
INSERT INTO `address_areas` VALUES (1139, '341822', '广德县', '341800');
INSERT INTO `address_areas` VALUES (1140, '341823', '泾　县', '341800');
INSERT INTO `address_areas` VALUES (1141, '341824', '绩溪县', '341800');
INSERT INTO `address_areas` VALUES (1142, '341825', '旌德县', '341800');
INSERT INTO `address_areas` VALUES (1143, '341881', '宁国市', '341800');
INSERT INTO `address_areas` VALUES (1144, '350101', '市辖区', '350100');
INSERT INTO `address_areas` VALUES (1145, '350102', '鼓楼区', '350100');
INSERT INTO `address_areas` VALUES (1146, '350103', '台江区', '350100');
INSERT INTO `address_areas` VALUES (1147, '350104', '仓山区', '350100');
INSERT INTO `address_areas` VALUES (1148, '350105', '马尾区', '350100');
INSERT INTO `address_areas` VALUES (1149, '350111', '晋安区', '350100');
INSERT INTO `address_areas` VALUES (1150, '350121', '闽侯县', '350100');
INSERT INTO `address_areas` VALUES (1151, '350122', '连江县', '350100');
INSERT INTO `address_areas` VALUES (1152, '350123', '罗源县', '350100');
INSERT INTO `address_areas` VALUES (1153, '350124', '闽清县', '350100');
INSERT INTO `address_areas` VALUES (1154, '350125', '永泰县', '350100');
INSERT INTO `address_areas` VALUES (1155, '350128', '平潭县', '350100');
INSERT INTO `address_areas` VALUES (1156, '350181', '福清市', '350100');
INSERT INTO `address_areas` VALUES (1157, '350182', '长乐市', '350100');
INSERT INTO `address_areas` VALUES (1158, '350201', '市辖区', '350200');
INSERT INTO `address_areas` VALUES (1159, '350203', '思明区', '350200');
INSERT INTO `address_areas` VALUES (1160, '350205', '海沧区', '350200');
INSERT INTO `address_areas` VALUES (1161, '350206', '湖里区', '350200');
INSERT INTO `address_areas` VALUES (1162, '350211', '集美区', '350200');
INSERT INTO `address_areas` VALUES (1163, '350212', '同安区', '350200');
INSERT INTO `address_areas` VALUES (1164, '350213', '翔安区', '350200');
INSERT INTO `address_areas` VALUES (1165, '350301', '市辖区', '350300');
INSERT INTO `address_areas` VALUES (1166, '350302', '城厢区', '350300');
INSERT INTO `address_areas` VALUES (1167, '350303', '涵江区', '350300');
INSERT INTO `address_areas` VALUES (1168, '350304', '荔城区', '350300');
INSERT INTO `address_areas` VALUES (1169, '350305', '秀屿区', '350300');
INSERT INTO `address_areas` VALUES (1170, '350322', '仙游县', '350300');
INSERT INTO `address_areas` VALUES (1171, '350401', '市辖区', '350400');
INSERT INTO `address_areas` VALUES (1172, '350402', '梅列区', '350400');
INSERT INTO `address_areas` VALUES (1173, '350403', '三元区', '350400');
INSERT INTO `address_areas` VALUES (1174, '350421', '明溪县', '350400');
INSERT INTO `address_areas` VALUES (1175, '350423', '清流县', '350400');
INSERT INTO `address_areas` VALUES (1176, '350424', '宁化县', '350400');
INSERT INTO `address_areas` VALUES (1177, '350425', '大田县', '350400');
INSERT INTO `address_areas` VALUES (1178, '350426', '尤溪县', '350400');
INSERT INTO `address_areas` VALUES (1179, '350427', '沙　县', '350400');
INSERT INTO `address_areas` VALUES (1180, '350428', '将乐县', '350400');
INSERT INTO `address_areas` VALUES (1181, '350429', '泰宁县', '350400');
INSERT INTO `address_areas` VALUES (1182, '350430', '建宁县', '350400');
INSERT INTO `address_areas` VALUES (1183, '350481', '永安市', '350400');
INSERT INTO `address_areas` VALUES (1184, '350501', '市辖区', '350500');
INSERT INTO `address_areas` VALUES (1185, '350502', '鲤城区', '350500');
INSERT INTO `address_areas` VALUES (1186, '350503', '丰泽区', '350500');
INSERT INTO `address_areas` VALUES (1187, '350504', '洛江区', '350500');
INSERT INTO `address_areas` VALUES (1188, '350505', '泉港区', '350500');
INSERT INTO `address_areas` VALUES (1189, '350521', '惠安县', '350500');
INSERT INTO `address_areas` VALUES (1190, '350524', '安溪县', '350500');
INSERT INTO `address_areas` VALUES (1191, '350525', '永春县', '350500');
INSERT INTO `address_areas` VALUES (1192, '350526', '德化县', '350500');
INSERT INTO `address_areas` VALUES (1193, '350527', '金门县', '350500');
INSERT INTO `address_areas` VALUES (1194, '350581', '石狮市', '350500');
INSERT INTO `address_areas` VALUES (1195, '350582', '晋江市', '350500');
INSERT INTO `address_areas` VALUES (1196, '350583', '南安市', '350500');
INSERT INTO `address_areas` VALUES (1197, '350601', '市辖区', '350600');
INSERT INTO `address_areas` VALUES (1198, '350602', '芗城区', '350600');
INSERT INTO `address_areas` VALUES (1199, '350603', '龙文区', '350600');
INSERT INTO `address_areas` VALUES (1200, '350622', '云霄县', '350600');
INSERT INTO `address_areas` VALUES (1201, '350623', '漳浦县', '350600');
INSERT INTO `address_areas` VALUES (1202, '350624', '诏安县', '350600');
INSERT INTO `address_areas` VALUES (1203, '350625', '长泰县', '350600');
INSERT INTO `address_areas` VALUES (1204, '350626', '东山县', '350600');
INSERT INTO `address_areas` VALUES (1205, '350627', '南靖县', '350600');
INSERT INTO `address_areas` VALUES (1206, '350628', '平和县', '350600');
INSERT INTO `address_areas` VALUES (1207, '350629', '华安县', '350600');
INSERT INTO `address_areas` VALUES (1208, '350681', '龙海市', '350600');
INSERT INTO `address_areas` VALUES (1209, '350701', '市辖区', '350700');
INSERT INTO `address_areas` VALUES (1210, '350702', '延平区', '350700');
INSERT INTO `address_areas` VALUES (1211, '350721', '顺昌县', '350700');
INSERT INTO `address_areas` VALUES (1212, '350722', '浦城县', '350700');
INSERT INTO `address_areas` VALUES (1213, '350723', '光泽县', '350700');
INSERT INTO `address_areas` VALUES (1214, '350724', '松溪县', '350700');
INSERT INTO `address_areas` VALUES (1215, '350725', '政和县', '350700');
INSERT INTO `address_areas` VALUES (1216, '350781', '邵武市', '350700');
INSERT INTO `address_areas` VALUES (1217, '350782', '武夷山市', '350700');
INSERT INTO `address_areas` VALUES (1218, '350783', '建瓯市', '350700');
INSERT INTO `address_areas` VALUES (1219, '350784', '建阳市', '350700');
INSERT INTO `address_areas` VALUES (1220, '350801', '市辖区', '350800');
INSERT INTO `address_areas` VALUES (1221, '350802', '新罗区', '350800');
INSERT INTO `address_areas` VALUES (1222, '350821', '长汀县', '350800');
INSERT INTO `address_areas` VALUES (1223, '350822', '永定县', '350800');
INSERT INTO `address_areas` VALUES (1224, '350823', '上杭县', '350800');
INSERT INTO `address_areas` VALUES (1225, '350824', '武平县', '350800');
INSERT INTO `address_areas` VALUES (1226, '350825', '连城县', '350800');
INSERT INTO `address_areas` VALUES (1227, '350881', '漳平市', '350800');
INSERT INTO `address_areas` VALUES (1228, '350901', '市辖区', '350900');
INSERT INTO `address_areas` VALUES (1229, '350902', '蕉城区', '350900');
INSERT INTO `address_areas` VALUES (1230, '350921', '霞浦县', '350900');
INSERT INTO `address_areas` VALUES (1231, '350922', '古田县', '350900');
INSERT INTO `address_areas` VALUES (1232, '350923', '屏南县', '350900');
INSERT INTO `address_areas` VALUES (1233, '350924', '寿宁县', '350900');
INSERT INTO `address_areas` VALUES (1234, '350925', '周宁县', '350900');
INSERT INTO `address_areas` VALUES (1235, '350926', '柘荣县', '350900');
INSERT INTO `address_areas` VALUES (1236, '350981', '福安市', '350900');
INSERT INTO `address_areas` VALUES (1237, '350982', '福鼎市', '350900');
INSERT INTO `address_areas` VALUES (1238, '360101', '市辖区', '360100');
INSERT INTO `address_areas` VALUES (1239, '360102', '东湖区', '360100');
INSERT INTO `address_areas` VALUES (1240, '360103', '西湖区', '360100');
INSERT INTO `address_areas` VALUES (1241, '360104', '青云谱区', '360100');
INSERT INTO `address_areas` VALUES (1242, '360105', '湾里区', '360100');
INSERT INTO `address_areas` VALUES (1243, '360111', '青山湖区', '360100');
INSERT INTO `address_areas` VALUES (1244, '360121', '南昌县', '360100');
INSERT INTO `address_areas` VALUES (1245, '360122', '新建县', '360100');
INSERT INTO `address_areas` VALUES (1246, '360123', '安义县', '360100');
INSERT INTO `address_areas` VALUES (1247, '360124', '进贤县', '360100');
INSERT INTO `address_areas` VALUES (1248, '360201', '市辖区', '360200');
INSERT INTO `address_areas` VALUES (1249, '360202', '昌江区', '360200');
INSERT INTO `address_areas` VALUES (1250, '360203', '珠山区', '360200');
INSERT INTO `address_areas` VALUES (1251, '360222', '浮梁县', '360200');
INSERT INTO `address_areas` VALUES (1252, '360281', '乐平市', '360200');
INSERT INTO `address_areas` VALUES (1253, '360301', '市辖区', '360300');
INSERT INTO `address_areas` VALUES (1254, '360302', '安源区', '360300');
INSERT INTO `address_areas` VALUES (1255, '360313', '湘东区', '360300');
INSERT INTO `address_areas` VALUES (1256, '360321', '莲花县', '360300');
INSERT INTO `address_areas` VALUES (1257, '360322', '上栗县', '360300');
INSERT INTO `address_areas` VALUES (1258, '360323', '芦溪县', '360300');
INSERT INTO `address_areas` VALUES (1259, '360401', '市辖区', '360400');
INSERT INTO `address_areas` VALUES (1260, '360402', '庐山区', '360400');
INSERT INTO `address_areas` VALUES (1261, '360403', '浔阳区', '360400');
INSERT INTO `address_areas` VALUES (1262, '360421', '九江县', '360400');
INSERT INTO `address_areas` VALUES (1263, '360423', '武宁县', '360400');
INSERT INTO `address_areas` VALUES (1264, '360424', '修水县', '360400');
INSERT INTO `address_areas` VALUES (1265, '360425', '永修县', '360400');
INSERT INTO `address_areas` VALUES (1266, '360426', '德安县', '360400');
INSERT INTO `address_areas` VALUES (1267, '360427', '星子县', '360400');
INSERT INTO `address_areas` VALUES (1268, '360428', '都昌县', '360400');
INSERT INTO `address_areas` VALUES (1269, '360429', '湖口县', '360400');
INSERT INTO `address_areas` VALUES (1270, '360430', '彭泽县', '360400');
INSERT INTO `address_areas` VALUES (1271, '360481', '瑞昌市', '360400');
INSERT INTO `address_areas` VALUES (1272, '360501', '市辖区', '360500');
INSERT INTO `address_areas` VALUES (1273, '360502', '渝水区', '360500');
INSERT INTO `address_areas` VALUES (1274, '360521', '分宜县', '360500');
INSERT INTO `address_areas` VALUES (1275, '360601', '市辖区', '360600');
INSERT INTO `address_areas` VALUES (1276, '360602', '月湖区', '360600');
INSERT INTO `address_areas` VALUES (1277, '360622', '余江县', '360600');
INSERT INTO `address_areas` VALUES (1278, '360681', '贵溪市', '360600');
INSERT INTO `address_areas` VALUES (1279, '360701', '市辖区', '360700');
INSERT INTO `address_areas` VALUES (1280, '360702', '章贡区', '360700');
INSERT INTO `address_areas` VALUES (1281, '360721', '赣　县', '360700');
INSERT INTO `address_areas` VALUES (1282, '360722', '信丰县', '360700');
INSERT INTO `address_areas` VALUES (1283, '360723', '大余县', '360700');
INSERT INTO `address_areas` VALUES (1284, '360724', '上犹县', '360700');
INSERT INTO `address_areas` VALUES (1285, '360725', '崇义县', '360700');
INSERT INTO `address_areas` VALUES (1286, '360726', '安远县', '360700');
INSERT INTO `address_areas` VALUES (1287, '360727', '龙南县', '360700');
INSERT INTO `address_areas` VALUES (1288, '360728', '定南县', '360700');
INSERT INTO `address_areas` VALUES (1289, '360729', '全南县', '360700');
INSERT INTO `address_areas` VALUES (1290, '360730', '宁都县', '360700');
INSERT INTO `address_areas` VALUES (1291, '360731', '于都县', '360700');
INSERT INTO `address_areas` VALUES (1292, '360732', '兴国县', '360700');
INSERT INTO `address_areas` VALUES (1293, '360733', '会昌县', '360700');
INSERT INTO `address_areas` VALUES (1294, '360734', '寻乌县', '360700');
INSERT INTO `address_areas` VALUES (1295, '360735', '石城县', '360700');
INSERT INTO `address_areas` VALUES (1296, '360781', '瑞金市', '360700');
INSERT INTO `address_areas` VALUES (1297, '360782', '南康市', '360700');
INSERT INTO `address_areas` VALUES (1298, '360801', '市辖区', '360800');
INSERT INTO `address_areas` VALUES (1299, '360802', '吉州区', '360800');
INSERT INTO `address_areas` VALUES (1300, '360803', '青原区', '360800');
INSERT INTO `address_areas` VALUES (1301, '360821', '吉安县', '360800');
INSERT INTO `address_areas` VALUES (1302, '360822', '吉水县', '360800');
INSERT INTO `address_areas` VALUES (1303, '360823', '峡江县', '360800');
INSERT INTO `address_areas` VALUES (1304, '360824', '新干县', '360800');
INSERT INTO `address_areas` VALUES (1305, '360825', '永丰县', '360800');
INSERT INTO `address_areas` VALUES (1306, '360826', '泰和县', '360800');
INSERT INTO `address_areas` VALUES (1307, '360827', '遂川县', '360800');
INSERT INTO `address_areas` VALUES (1308, '360828', '万安县', '360800');
INSERT INTO `address_areas` VALUES (1309, '360829', '安福县', '360800');
INSERT INTO `address_areas` VALUES (1310, '360830', '永新县', '360800');
INSERT INTO `address_areas` VALUES (1311, '360881', '井冈山市', '360800');
INSERT INTO `address_areas` VALUES (1312, '360901', '市辖区', '360900');
INSERT INTO `address_areas` VALUES (1313, '360902', '袁州区', '360900');
INSERT INTO `address_areas` VALUES (1314, '360921', '奉新县', '360900');
INSERT INTO `address_areas` VALUES (1315, '360922', '万载县', '360900');
INSERT INTO `address_areas` VALUES (1316, '360923', '上高县', '360900');
INSERT INTO `address_areas` VALUES (1317, '360924', '宜丰县', '360900');
INSERT INTO `address_areas` VALUES (1318, '360925', '靖安县', '360900');
INSERT INTO `address_areas` VALUES (1319, '360926', '铜鼓县', '360900');
INSERT INTO `address_areas` VALUES (1320, '360981', '丰城市', '360900');
INSERT INTO `address_areas` VALUES (1321, '360982', '樟树市', '360900');
INSERT INTO `address_areas` VALUES (1322, '360983', '高安市', '360900');
INSERT INTO `address_areas` VALUES (1323, '361001', '市辖区', '361000');
INSERT INTO `address_areas` VALUES (1324, '361002', '临川区', '361000');
INSERT INTO `address_areas` VALUES (1325, '361021', '南城县', '361000');
INSERT INTO `address_areas` VALUES (1326, '361022', '黎川县', '361000');
INSERT INTO `address_areas` VALUES (1327, '361023', '南丰县', '361000');
INSERT INTO `address_areas` VALUES (1328, '361024', '崇仁县', '361000');
INSERT INTO `address_areas` VALUES (1329, '361025', '乐安县', '361000');
INSERT INTO `address_areas` VALUES (1330, '361026', '宜黄县', '361000');
INSERT INTO `address_areas` VALUES (1331, '361027', '金溪县', '361000');
INSERT INTO `address_areas` VALUES (1332, '361028', '资溪县', '361000');
INSERT INTO `address_areas` VALUES (1333, '361029', '东乡县', '361000');
INSERT INTO `address_areas` VALUES (1334, '361030', '广昌县', '361000');
INSERT INTO `address_areas` VALUES (1335, '361101', '市辖区', '361100');
INSERT INTO `address_areas` VALUES (1336, '361102', '信州区', '361100');
INSERT INTO `address_areas` VALUES (1337, '361121', '上饶县', '361100');
INSERT INTO `address_areas` VALUES (1338, '361122', '广丰县', '361100');
INSERT INTO `address_areas` VALUES (1339, '361123', '玉山县', '361100');
INSERT INTO `address_areas` VALUES (1340, '361124', '铅山县', '361100');
INSERT INTO `address_areas` VALUES (1341, '361125', '横峰县', '361100');
INSERT INTO `address_areas` VALUES (1342, '361126', '弋阳县', '361100');
INSERT INTO `address_areas` VALUES (1343, '361127', '余干县', '361100');
INSERT INTO `address_areas` VALUES (1344, '361128', '鄱阳县', '361100');
INSERT INTO `address_areas` VALUES (1345, '361129', '万年县', '361100');
INSERT INTO `address_areas` VALUES (1346, '361130', '婺源县', '361100');
INSERT INTO `address_areas` VALUES (1347, '361181', '德兴市', '361100');
INSERT INTO `address_areas` VALUES (1348, '370101', '市辖区', '370100');
INSERT INTO `address_areas` VALUES (1349, '370102', '历下区', '370100');
INSERT INTO `address_areas` VALUES (1350, '370103', '市中区', '370100');
INSERT INTO `address_areas` VALUES (1351, '370104', '槐荫区', '370100');
INSERT INTO `address_areas` VALUES (1352, '370105', '天桥区', '370100');
INSERT INTO `address_areas` VALUES (1353, '370112', '历城区', '370100');
INSERT INTO `address_areas` VALUES (1354, '370113', '长清区', '370100');
INSERT INTO `address_areas` VALUES (1355, '370124', '平阴县', '370100');
INSERT INTO `address_areas` VALUES (1356, '370125', '济阳县', '370100');
INSERT INTO `address_areas` VALUES (1357, '370126', '商河县', '370100');
INSERT INTO `address_areas` VALUES (1358, '370181', '章丘市', '370100');
INSERT INTO `address_areas` VALUES (1359, '370201', '市辖区', '370200');
INSERT INTO `address_areas` VALUES (1360, '370202', '市南区', '370200');
INSERT INTO `address_areas` VALUES (1361, '370203', '市北区', '370200');
INSERT INTO `address_areas` VALUES (1362, '370205', '四方区', '370200');
INSERT INTO `address_areas` VALUES (1363, '370211', '黄岛区', '370200');
INSERT INTO `address_areas` VALUES (1364, '370212', '崂山区', '370200');
INSERT INTO `address_areas` VALUES (1365, '370213', '李沧区', '370200');
INSERT INTO `address_areas` VALUES (1366, '370214', '城阳区', '370200');
INSERT INTO `address_areas` VALUES (1367, '370281', '胶州市', '370200');
INSERT INTO `address_areas` VALUES (1368, '370282', '即墨市', '370200');
INSERT INTO `address_areas` VALUES (1369, '370283', '平度市', '370200');
INSERT INTO `address_areas` VALUES (1370, '370284', '胶南市', '370200');
INSERT INTO `address_areas` VALUES (1371, '370285', '莱西市', '370200');
INSERT INTO `address_areas` VALUES (1372, '370301', '市辖区', '370300');
INSERT INTO `address_areas` VALUES (1373, '370302', '淄川区', '370300');
INSERT INTO `address_areas` VALUES (1374, '370303', '张店区', '370300');
INSERT INTO `address_areas` VALUES (1375, '370304', '博山区', '370300');
INSERT INTO `address_areas` VALUES (1376, '370305', '临淄区', '370300');
INSERT INTO `address_areas` VALUES (1377, '370306', '周村区', '370300');
INSERT INTO `address_areas` VALUES (1378, '370321', '桓台县', '370300');
INSERT INTO `address_areas` VALUES (1379, '370322', '高青县', '370300');
INSERT INTO `address_areas` VALUES (1380, '370323', '沂源县', '370300');
INSERT INTO `address_areas` VALUES (1381, '370401', '市辖区', '370400');
INSERT INTO `address_areas` VALUES (1382, '370402', '市中区', '370400');
INSERT INTO `address_areas` VALUES (1383, '370403', '薛城区', '370400');
INSERT INTO `address_areas` VALUES (1384, '370404', '峄城区', '370400');
INSERT INTO `address_areas` VALUES (1385, '370405', '台儿庄区', '370400');
INSERT INTO `address_areas` VALUES (1386, '370406', '山亭区', '370400');
INSERT INTO `address_areas` VALUES (1387, '370481', '滕州市', '370400');
INSERT INTO `address_areas` VALUES (1388, '370501', '市辖区', '370500');
INSERT INTO `address_areas` VALUES (1389, '370502', '东营区', '370500');
INSERT INTO `address_areas` VALUES (1390, '370503', '河口区', '370500');
INSERT INTO `address_areas` VALUES (1391, '370521', '垦利县', '370500');
INSERT INTO `address_areas` VALUES (1392, '370522', '利津县', '370500');
INSERT INTO `address_areas` VALUES (1393, '370523', '广饶县', '370500');
INSERT INTO `address_areas` VALUES (1394, '370601', '市辖区', '370600');
INSERT INTO `address_areas` VALUES (1395, '370602', '芝罘区', '370600');
INSERT INTO `address_areas` VALUES (1396, '370611', '福山区', '370600');
INSERT INTO `address_areas` VALUES (1397, '370612', '牟平区', '370600');
INSERT INTO `address_areas` VALUES (1398, '370613', '莱山区', '370600');
INSERT INTO `address_areas` VALUES (1399, '370634', '长岛县', '370600');
INSERT INTO `address_areas` VALUES (1400, '370681', '龙口市', '370600');
INSERT INTO `address_areas` VALUES (1401, '370682', '莱阳市', '370600');
INSERT INTO `address_areas` VALUES (1402, '370683', '莱州市', '370600');
INSERT INTO `address_areas` VALUES (1403, '370684', '蓬莱市', '370600');
INSERT INTO `address_areas` VALUES (1404, '370685', '招远市', '370600');
INSERT INTO `address_areas` VALUES (1405, '370686', '栖霞市', '370600');
INSERT INTO `address_areas` VALUES (1406, '370687', '海阳市', '370600');
INSERT INTO `address_areas` VALUES (1407, '370701', '市辖区', '370700');
INSERT INTO `address_areas` VALUES (1408, '370702', '潍城区', '370700');
INSERT INTO `address_areas` VALUES (1409, '370703', '寒亭区', '370700');
INSERT INTO `address_areas` VALUES (1410, '370704', '坊子区', '370700');
INSERT INTO `address_areas` VALUES (1411, '370705', '奎文区', '370700');
INSERT INTO `address_areas` VALUES (1412, '370724', '临朐县', '370700');
INSERT INTO `address_areas` VALUES (1413, '370725', '昌乐县', '370700');
INSERT INTO `address_areas` VALUES (1414, '370781', '青州市', '370700');
INSERT INTO `address_areas` VALUES (1415, '370782', '诸城市', '370700');
INSERT INTO `address_areas` VALUES (1416, '370783', '寿光市', '370700');
INSERT INTO `address_areas` VALUES (1417, '370784', '安丘市', '370700');
INSERT INTO `address_areas` VALUES (1418, '370785', '高密市', '370700');
INSERT INTO `address_areas` VALUES (1419, '370786', '昌邑市', '370700');
INSERT INTO `address_areas` VALUES (1420, '370801', '市辖区', '370800');
INSERT INTO `address_areas` VALUES (1421, '370802', '市中区', '370800');
INSERT INTO `address_areas` VALUES (1422, '370811', '任城区', '370800');
INSERT INTO `address_areas` VALUES (1423, '370826', '微山县', '370800');
INSERT INTO `address_areas` VALUES (1424, '370827', '鱼台县', '370800');
INSERT INTO `address_areas` VALUES (1425, '370828', '金乡县', '370800');
INSERT INTO `address_areas` VALUES (1426, '370829', '嘉祥县', '370800');
INSERT INTO `address_areas` VALUES (1427, '370830', '汶上县', '370800');
INSERT INTO `address_areas` VALUES (1428, '370831', '泗水县', '370800');
INSERT INTO `address_areas` VALUES (1429, '370832', '梁山县', '370800');
INSERT INTO `address_areas` VALUES (1430, '370881', '曲阜市', '370800');
INSERT INTO `address_areas` VALUES (1431, '370882', '兖州市', '370800');
INSERT INTO `address_areas` VALUES (1432, '370883', '邹城市', '370800');
INSERT INTO `address_areas` VALUES (1433, '370901', '市辖区', '370900');
INSERT INTO `address_areas` VALUES (1434, '370902', '泰山区', '370900');
INSERT INTO `address_areas` VALUES (1435, '370903', '岱岳区', '370900');
INSERT INTO `address_areas` VALUES (1436, '370921', '宁阳县', '370900');
INSERT INTO `address_areas` VALUES (1437, '370923', '东平县', '370900');
INSERT INTO `address_areas` VALUES (1438, '370982', '新泰市', '370900');
INSERT INTO `address_areas` VALUES (1439, '370983', '肥城市', '370900');
INSERT INTO `address_areas` VALUES (1440, '371001', '市辖区', '371000');
INSERT INTO `address_areas` VALUES (1441, '371002', '环翠区', '371000');
INSERT INTO `address_areas` VALUES (1442, '371081', '文登市', '371000');
INSERT INTO `address_areas` VALUES (1443, '371082', '荣成市', '371000');
INSERT INTO `address_areas` VALUES (1444, '371083', '乳山市', '371000');
INSERT INTO `address_areas` VALUES (1445, '371101', '市辖区', '371100');
INSERT INTO `address_areas` VALUES (1446, '371102', '东港区', '371100');
INSERT INTO `address_areas` VALUES (1447, '371103', '岚山区', '371100');
INSERT INTO `address_areas` VALUES (1448, '371121', '五莲县', '371100');
INSERT INTO `address_areas` VALUES (1449, '371122', '莒　县', '371100');
INSERT INTO `address_areas` VALUES (1450, '371201', '市辖区', '371200');
INSERT INTO `address_areas` VALUES (1451, '371202', '莱城区', '371200');
INSERT INTO `address_areas` VALUES (1452, '371203', '钢城区', '371200');
INSERT INTO `address_areas` VALUES (1453, '371301', '市辖区', '371300');
INSERT INTO `address_areas` VALUES (1454, '371302', '兰山区', '371300');
INSERT INTO `address_areas` VALUES (1455, '371311', '罗庄区', '371300');
INSERT INTO `address_areas` VALUES (1456, '371312', '河东区', '371300');
INSERT INTO `address_areas` VALUES (1457, '371321', '沂南县', '371300');
INSERT INTO `address_areas` VALUES (1458, '371322', '郯城县', '371300');
INSERT INTO `address_areas` VALUES (1459, '371323', '沂水县', '371300');
INSERT INTO `address_areas` VALUES (1460, '371324', '苍山县', '371300');
INSERT INTO `address_areas` VALUES (1461, '371325', '费　县', '371300');
INSERT INTO `address_areas` VALUES (1462, '371326', '平邑县', '371300');
INSERT INTO `address_areas` VALUES (1463, '371327', '莒南县', '371300');
INSERT INTO `address_areas` VALUES (1464, '371328', '蒙阴县', '371300');
INSERT INTO `address_areas` VALUES (1465, '371329', '临沭县', '371300');
INSERT INTO `address_areas` VALUES (1466, '371401', '市辖区', '371400');
INSERT INTO `address_areas` VALUES (1467, '371402', '德城区', '371400');
INSERT INTO `address_areas` VALUES (1468, '371421', '陵　县', '371400');
INSERT INTO `address_areas` VALUES (1469, '371422', '宁津县', '371400');
INSERT INTO `address_areas` VALUES (1470, '371423', '庆云县', '371400');
INSERT INTO `address_areas` VALUES (1471, '371424', '临邑县', '371400');
INSERT INTO `address_areas` VALUES (1472, '371425', '齐河县', '371400');
INSERT INTO `address_areas` VALUES (1473, '371426', '平原县', '371400');
INSERT INTO `address_areas` VALUES (1474, '371427', '夏津县', '371400');
INSERT INTO `address_areas` VALUES (1475, '371428', '武城县', '371400');
INSERT INTO `address_areas` VALUES (1476, '371481', '乐陵市', '371400');
INSERT INTO `address_areas` VALUES (1477, '371482', '禹城市', '371400');
INSERT INTO `address_areas` VALUES (1478, '371501', '市辖区', '371500');
INSERT INTO `address_areas` VALUES (1479, '371502', '东昌府区', '371500');
INSERT INTO `address_areas` VALUES (1480, '371521', '阳谷县', '371500');
INSERT INTO `address_areas` VALUES (1481, '371522', '莘　县', '371500');
INSERT INTO `address_areas` VALUES (1482, '371523', '茌平县', '371500');
INSERT INTO `address_areas` VALUES (1483, '371524', '东阿县', '371500');
INSERT INTO `address_areas` VALUES (1484, '371525', '冠　县', '371500');
INSERT INTO `address_areas` VALUES (1485, '371526', '高唐县', '371500');
INSERT INTO `address_areas` VALUES (1486, '371581', '临清市', '371500');
INSERT INTO `address_areas` VALUES (1487, '371601', '市辖区', '371600');
INSERT INTO `address_areas` VALUES (1488, '371602', '滨城区', '371600');
INSERT INTO `address_areas` VALUES (1489, '371621', '惠民县', '371600');
INSERT INTO `address_areas` VALUES (1490, '371622', '阳信县', '371600');
INSERT INTO `address_areas` VALUES (1491, '371623', '无棣县', '371600');
INSERT INTO `address_areas` VALUES (1492, '371624', '沾化县', '371600');
INSERT INTO `address_areas` VALUES (1493, '371625', '博兴县', '371600');
INSERT INTO `address_areas` VALUES (1494, '371626', '邹平县', '371600');
INSERT INTO `address_areas` VALUES (1495, '371701', '市辖区', '371700');
INSERT INTO `address_areas` VALUES (1496, '371702', '牡丹区', '371700');
INSERT INTO `address_areas` VALUES (1497, '371721', '曹　县', '371700');
INSERT INTO `address_areas` VALUES (1498, '371722', '单　县', '371700');
INSERT INTO `address_areas` VALUES (1499, '371723', '成武县', '371700');
INSERT INTO `address_areas` VALUES (1500, '371724', '巨野县', '371700');
INSERT INTO `address_areas` VALUES (1501, '371725', '郓城县', '371700');
INSERT INTO `address_areas` VALUES (1502, '371726', '鄄城县', '371700');
INSERT INTO `address_areas` VALUES (1503, '371727', '定陶县', '371700');
INSERT INTO `address_areas` VALUES (1504, '371728', '东明县', '371700');
INSERT INTO `address_areas` VALUES (1505, '410101', '市辖区', '410100');
INSERT INTO `address_areas` VALUES (1506, '410102', '中原区', '410100');
INSERT INTO `address_areas` VALUES (1507, '410103', '二七区', '410100');
INSERT INTO `address_areas` VALUES (1508, '410104', '管城回族区', '410100');
INSERT INTO `address_areas` VALUES (1509, '410105', '金水区', '410100');
INSERT INTO `address_areas` VALUES (1510, '410106', '上街区', '410100');
INSERT INTO `address_areas` VALUES (1511, '410108', '邙山区', '410100');
INSERT INTO `address_areas` VALUES (1512, '410122', '中牟县', '410100');
INSERT INTO `address_areas` VALUES (1513, '410181', '巩义市', '410100');
INSERT INTO `address_areas` VALUES (1514, '410182', '荥阳市', '410100');
INSERT INTO `address_areas` VALUES (1515, '410183', '新密市', '410100');
INSERT INTO `address_areas` VALUES (1516, '410184', '新郑市', '410100');
INSERT INTO `address_areas` VALUES (1517, '410185', '登封市', '410100');
INSERT INTO `address_areas` VALUES (1518, '410201', '市辖区', '410200');
INSERT INTO `address_areas` VALUES (1519, '410202', '龙亭区', '410200');
INSERT INTO `address_areas` VALUES (1520, '410203', '顺河回族区', '410200');
INSERT INTO `address_areas` VALUES (1521, '410204', '鼓楼区', '410200');
INSERT INTO `address_areas` VALUES (1522, '410205', '南关区', '410200');
INSERT INTO `address_areas` VALUES (1523, '410211', '郊　区', '410200');
INSERT INTO `address_areas` VALUES (1524, '410221', '杞　县', '410200');
INSERT INTO `address_areas` VALUES (1525, '410222', '通许县', '410200');
INSERT INTO `address_areas` VALUES (1526, '410223', '尉氏县', '410200');
INSERT INTO `address_areas` VALUES (1527, '410224', '开封县', '410200');
INSERT INTO `address_areas` VALUES (1528, '410225', '兰考县', '410200');
INSERT INTO `address_areas` VALUES (1529, '410301', '市辖区', '410300');
INSERT INTO `address_areas` VALUES (1530, '410302', '老城区', '410300');
INSERT INTO `address_areas` VALUES (1531, '410303', '西工区', '410300');
INSERT INTO `address_areas` VALUES (1532, '410304', '廛河回族区', '410300');
INSERT INTO `address_areas` VALUES (1533, '410305', '涧西区', '410300');
INSERT INTO `address_areas` VALUES (1534, '410306', '吉利区', '410300');
INSERT INTO `address_areas` VALUES (1535, '410307', '洛龙区', '410300');
INSERT INTO `address_areas` VALUES (1536, '410322', '孟津县', '410300');
INSERT INTO `address_areas` VALUES (1537, '410323', '新安县', '410300');
INSERT INTO `address_areas` VALUES (1538, '410324', '栾川县', '410300');
INSERT INTO `address_areas` VALUES (1539, '410325', '嵩　县', '410300');
INSERT INTO `address_areas` VALUES (1540, '410326', '汝阳县', '410300');
INSERT INTO `address_areas` VALUES (1541, '410327', '宜阳县', '410300');
INSERT INTO `address_areas` VALUES (1542, '410328', '洛宁县', '410300');
INSERT INTO `address_areas` VALUES (1543, '410329', '伊川县', '410300');
INSERT INTO `address_areas` VALUES (1544, '410381', '偃师市', '410300');
INSERT INTO `address_areas` VALUES (1545, '410401', '市辖区', '410400');
INSERT INTO `address_areas` VALUES (1546, '410402', '新华区', '410400');
INSERT INTO `address_areas` VALUES (1547, '410403', '卫东区', '410400');
INSERT INTO `address_areas` VALUES (1548, '410404', '石龙区', '410400');
INSERT INTO `address_areas` VALUES (1549, '410411', '湛河区', '410400');
INSERT INTO `address_areas` VALUES (1550, '410421', '宝丰县', '410400');
INSERT INTO `address_areas` VALUES (1551, '410422', '叶　县', '410400');
INSERT INTO `address_areas` VALUES (1552, '410423', '鲁山县', '410400');
INSERT INTO `address_areas` VALUES (1553, '410425', '郏　县', '410400');
INSERT INTO `address_areas` VALUES (1554, '410481', '舞钢市', '410400');
INSERT INTO `address_areas` VALUES (1555, '410482', '汝州市', '410400');
INSERT INTO `address_areas` VALUES (1556, '410501', '市辖区', '410500');
INSERT INTO `address_areas` VALUES (1557, '410502', '文峰区', '410500');
INSERT INTO `address_areas` VALUES (1558, '410503', '北关区', '410500');
INSERT INTO `address_areas` VALUES (1559, '410505', '殷都区', '410500');
INSERT INTO `address_areas` VALUES (1560, '410506', '龙安区', '410500');
INSERT INTO `address_areas` VALUES (1561, '410522', '安阳县', '410500');
INSERT INTO `address_areas` VALUES (1562, '410523', '汤阴县', '410500');
INSERT INTO `address_areas` VALUES (1563, '410526', '滑　县', '410500');
INSERT INTO `address_areas` VALUES (1564, '410527', '内黄县', '410500');
INSERT INTO `address_areas` VALUES (1565, '410581', '林州市', '410500');
INSERT INTO `address_areas` VALUES (1566, '410601', '市辖区', '410600');
INSERT INTO `address_areas` VALUES (1567, '410602', '鹤山区', '410600');
INSERT INTO `address_areas` VALUES (1568, '410603', '山城区', '410600');
INSERT INTO `address_areas` VALUES (1569, '410611', '淇滨区', '410600');
INSERT INTO `address_areas` VALUES (1570, '410621', '浚　县', '410600');
INSERT INTO `address_areas` VALUES (1571, '410622', '淇　县', '410600');
INSERT INTO `address_areas` VALUES (1572, '410701', '市辖区', '410700');
INSERT INTO `address_areas` VALUES (1573, '410702', '红旗区', '410700');
INSERT INTO `address_areas` VALUES (1574, '410703', '卫滨区', '410700');
INSERT INTO `address_areas` VALUES (1575, '410704', '凤泉区', '410700');
INSERT INTO `address_areas` VALUES (1576, '410711', '牧野区', '410700');
INSERT INTO `address_areas` VALUES (1577, '410721', '新乡县', '410700');
INSERT INTO `address_areas` VALUES (1578, '410724', '获嘉县', '410700');
INSERT INTO `address_areas` VALUES (1579, '410725', '原阳县', '410700');
INSERT INTO `address_areas` VALUES (1580, '410726', '延津县', '410700');
INSERT INTO `address_areas` VALUES (1581, '410727', '封丘县', '410700');
INSERT INTO `address_areas` VALUES (1582, '410728', '长垣县', '410700');
INSERT INTO `address_areas` VALUES (1583, '410781', '卫辉市', '410700');
INSERT INTO `address_areas` VALUES (1584, '410782', '辉县市', '410700');
INSERT INTO `address_areas` VALUES (1585, '410801', '市辖区', '410800');
INSERT INTO `address_areas` VALUES (1586, '410802', '解放区', '410800');
INSERT INTO `address_areas` VALUES (1587, '410803', '中站区', '410800');
INSERT INTO `address_areas` VALUES (1588, '410804', '马村区', '410800');
INSERT INTO `address_areas` VALUES (1589, '410811', '山阳区', '410800');
INSERT INTO `address_areas` VALUES (1590, '410821', '修武县', '410800');
INSERT INTO `address_areas` VALUES (1591, '410822', '博爱县', '410800');
INSERT INTO `address_areas` VALUES (1592, '410823', '武陟县', '410800');
INSERT INTO `address_areas` VALUES (1593, '410825', '温　县', '410800');
INSERT INTO `address_areas` VALUES (1594, '410881', '济源市', '410800');
INSERT INTO `address_areas` VALUES (1595, '410882', '沁阳市', '410800');
INSERT INTO `address_areas` VALUES (1596, '410883', '孟州市', '410800');
INSERT INTO `address_areas` VALUES (1597, '410901', '市辖区', '410900');
INSERT INTO `address_areas` VALUES (1598, '410902', '华龙区', '410900');
INSERT INTO `address_areas` VALUES (1599, '410922', '清丰县', '410900');
INSERT INTO `address_areas` VALUES (1600, '410923', '南乐县', '410900');
INSERT INTO `address_areas` VALUES (1601, '410926', '范　县', '410900');
INSERT INTO `address_areas` VALUES (1602, '410927', '台前县', '410900');
INSERT INTO `address_areas` VALUES (1603, '410928', '濮阳县', '410900');
INSERT INTO `address_areas` VALUES (1604, '411001', '市辖区', '411000');
INSERT INTO `address_areas` VALUES (1605, '411002', '魏都区', '411000');
INSERT INTO `address_areas` VALUES (1606, '411023', '许昌县', '411000');
INSERT INTO `address_areas` VALUES (1607, '411024', '鄢陵县', '411000');
INSERT INTO `address_areas` VALUES (1608, '411025', '襄城县', '411000');
INSERT INTO `address_areas` VALUES (1609, '411081', '禹州市', '411000');
INSERT INTO `address_areas` VALUES (1610, '411082', '长葛市', '411000');
INSERT INTO `address_areas` VALUES (1611, '411101', '市辖区', '411100');
INSERT INTO `address_areas` VALUES (1612, '411102', '源汇区', '411100');
INSERT INTO `address_areas` VALUES (1613, '411103', '郾城区', '411100');
INSERT INTO `address_areas` VALUES (1614, '411104', '召陵区', '411100');
INSERT INTO `address_areas` VALUES (1615, '411121', '舞阳县', '411100');
INSERT INTO `address_areas` VALUES (1616, '411122', '临颍县', '411100');
INSERT INTO `address_areas` VALUES (1617, '411201', '市辖区', '411200');
INSERT INTO `address_areas` VALUES (1618, '411202', '湖滨区', '411200');
INSERT INTO `address_areas` VALUES (1619, '411221', '渑池县', '411200');
INSERT INTO `address_areas` VALUES (1620, '411222', '陕　县', '411200');
INSERT INTO `address_areas` VALUES (1621, '411224', '卢氏县', '411200');
INSERT INTO `address_areas` VALUES (1622, '411281', '义马市', '411200');
INSERT INTO `address_areas` VALUES (1623, '411282', '灵宝市', '411200');
INSERT INTO `address_areas` VALUES (1624, '411301', '市辖区', '411300');
INSERT INTO `address_areas` VALUES (1625, '411302', '宛城区', '411300');
INSERT INTO `address_areas` VALUES (1626, '411303', '卧龙区', '411300');
INSERT INTO `address_areas` VALUES (1627, '411321', '南召县', '411300');
INSERT INTO `address_areas` VALUES (1628, '411322', '方城县', '411300');
INSERT INTO `address_areas` VALUES (1629, '411323', '西峡县', '411300');
INSERT INTO `address_areas` VALUES (1630, '411324', '镇平县', '411300');
INSERT INTO `address_areas` VALUES (1631, '411325', '内乡县', '411300');
INSERT INTO `address_areas` VALUES (1632, '411326', '淅川县', '411300');
INSERT INTO `address_areas` VALUES (1633, '411327', '社旗县', '411300');
INSERT INTO `address_areas` VALUES (1634, '411328', '唐河县', '411300');
INSERT INTO `address_areas` VALUES (1635, '411329', '新野县', '411300');
INSERT INTO `address_areas` VALUES (1636, '411330', '桐柏县', '411300');
INSERT INTO `address_areas` VALUES (1637, '411381', '邓州市', '411300');
INSERT INTO `address_areas` VALUES (1638, '411401', '市辖区', '411400');
INSERT INTO `address_areas` VALUES (1639, '411402', '梁园区', '411400');
INSERT INTO `address_areas` VALUES (1640, '411403', '睢阳区', '411400');
INSERT INTO `address_areas` VALUES (1641, '411421', '民权县', '411400');
INSERT INTO `address_areas` VALUES (1642, '411422', '睢　县', '411400');
INSERT INTO `address_areas` VALUES (1643, '411423', '宁陵县', '411400');
INSERT INTO `address_areas` VALUES (1644, '411424', '柘城县', '411400');
INSERT INTO `address_areas` VALUES (1645, '411425', '虞城县', '411400');
INSERT INTO `address_areas` VALUES (1646, '411426', '夏邑县', '411400');
INSERT INTO `address_areas` VALUES (1647, '411481', '永城市', '411400');
INSERT INTO `address_areas` VALUES (1648, '411501', '市辖区', '411500');
INSERT INTO `address_areas` VALUES (1649, '411502', '师河区', '411500');
INSERT INTO `address_areas` VALUES (1650, '411503', '平桥区', '411500');
INSERT INTO `address_areas` VALUES (1651, '411521', '罗山县', '411500');
INSERT INTO `address_areas` VALUES (1652, '411522', '光山县', '411500');
INSERT INTO `address_areas` VALUES (1653, '411523', '新　县', '411500');
INSERT INTO `address_areas` VALUES (1654, '411524', '商城县', '411500');
INSERT INTO `address_areas` VALUES (1655, '411525', '固始县', '411500');
INSERT INTO `address_areas` VALUES (1656, '411526', '潢川县', '411500');
INSERT INTO `address_areas` VALUES (1657, '411527', '淮滨县', '411500');
INSERT INTO `address_areas` VALUES (1658, '411528', '息　县', '411500');
INSERT INTO `address_areas` VALUES (1659, '411601', '市辖区', '411600');
INSERT INTO `address_areas` VALUES (1660, '411602', '川汇区', '411600');
INSERT INTO `address_areas` VALUES (1661, '411621', '扶沟县', '411600');
INSERT INTO `address_areas` VALUES (1662, '411622', '西华县', '411600');
INSERT INTO `address_areas` VALUES (1663, '411623', '商水县', '411600');
INSERT INTO `address_areas` VALUES (1664, '411624', '沈丘县', '411600');
INSERT INTO `address_areas` VALUES (1665, '411625', '郸城县', '411600');
INSERT INTO `address_areas` VALUES (1666, '411626', '淮阳县', '411600');
INSERT INTO `address_areas` VALUES (1667, '411627', '太康县', '411600');
INSERT INTO `address_areas` VALUES (1668, '411628', '鹿邑县', '411600');
INSERT INTO `address_areas` VALUES (1669, '411681', '项城市', '411600');
INSERT INTO `address_areas` VALUES (1670, '411701', '市辖区', '411700');
INSERT INTO `address_areas` VALUES (1671, '411702', '驿城区', '411700');
INSERT INTO `address_areas` VALUES (1672, '411721', '西平县', '411700');
INSERT INTO `address_areas` VALUES (1673, '411722', '上蔡县', '411700');
INSERT INTO `address_areas` VALUES (1674, '411723', '平舆县', '411700');
INSERT INTO `address_areas` VALUES (1675, '411724', '正阳县', '411700');
INSERT INTO `address_areas` VALUES (1676, '411725', '确山县', '411700');
INSERT INTO `address_areas` VALUES (1677, '411726', '泌阳县', '411700');
INSERT INTO `address_areas` VALUES (1678, '411727', '汝南县', '411700');
INSERT INTO `address_areas` VALUES (1679, '411728', '遂平县', '411700');
INSERT INTO `address_areas` VALUES (1680, '411729', '新蔡县', '411700');
INSERT INTO `address_areas` VALUES (1681, '420101', '市辖区', '420100');
INSERT INTO `address_areas` VALUES (1682, '420102', '江岸区', '420100');
INSERT INTO `address_areas` VALUES (1683, '420103', '江汉区', '420100');
INSERT INTO `address_areas` VALUES (1684, '420104', '乔口区', '420100');
INSERT INTO `address_areas` VALUES (1685, '420105', '汉阳区', '420100');
INSERT INTO `address_areas` VALUES (1686, '420106', '武昌区', '420100');
INSERT INTO `address_areas` VALUES (1687, '420107', '青山区', '420100');
INSERT INTO `address_areas` VALUES (1688, '420111', '洪山区', '420100');
INSERT INTO `address_areas` VALUES (1689, '420112', '东西湖区', '420100');
INSERT INTO `address_areas` VALUES (1690, '420113', '汉南区', '420100');
INSERT INTO `address_areas` VALUES (1691, '420114', '蔡甸区', '420100');
INSERT INTO `address_areas` VALUES (1692, '420115', '江夏区', '420100');
INSERT INTO `address_areas` VALUES (1693, '420116', '黄陂区', '420100');
INSERT INTO `address_areas` VALUES (1694, '420117', '新洲区', '420100');
INSERT INTO `address_areas` VALUES (1695, '420201', '市辖区', '420200');
INSERT INTO `address_areas` VALUES (1696, '420202', '黄石港区', '420200');
INSERT INTO `address_areas` VALUES (1697, '420203', '西塞山区', '420200');
INSERT INTO `address_areas` VALUES (1698, '420204', '下陆区', '420200');
INSERT INTO `address_areas` VALUES (1699, '420205', '铁山区', '420200');
INSERT INTO `address_areas` VALUES (1700, '420222', '阳新县', '420200');
INSERT INTO `address_areas` VALUES (1701, '420281', '大冶市', '420200');
INSERT INTO `address_areas` VALUES (1702, '420301', '市辖区', '420300');
INSERT INTO `address_areas` VALUES (1703, '420302', '茅箭区', '420300');
INSERT INTO `address_areas` VALUES (1704, '420303', '张湾区', '420300');
INSERT INTO `address_areas` VALUES (1705, '420321', '郧　县', '420300');
INSERT INTO `address_areas` VALUES (1706, '420322', '郧西县', '420300');
INSERT INTO `address_areas` VALUES (1707, '420323', '竹山县', '420300');
INSERT INTO `address_areas` VALUES (1708, '420324', '竹溪县', '420300');
INSERT INTO `address_areas` VALUES (1709, '420325', '房　县', '420300');
INSERT INTO `address_areas` VALUES (1710, '420381', '丹江口市', '420300');
INSERT INTO `address_areas` VALUES (1711, '420501', '市辖区', '420500');
INSERT INTO `address_areas` VALUES (1712, '420502', '西陵区', '420500');
INSERT INTO `address_areas` VALUES (1713, '420503', '伍家岗区', '420500');
INSERT INTO `address_areas` VALUES (1714, '420504', '点军区', '420500');
INSERT INTO `address_areas` VALUES (1715, '420505', '猇亭区', '420500');
INSERT INTO `address_areas` VALUES (1716, '420506', '夷陵区', '420500');
INSERT INTO `address_areas` VALUES (1717, '420525', '远安县', '420500');
INSERT INTO `address_areas` VALUES (1718, '420526', '兴山县', '420500');
INSERT INTO `address_areas` VALUES (1719, '420527', '秭归县', '420500');
INSERT INTO `address_areas` VALUES (1720, '420528', '长阳土家族自治县', '420500');
INSERT INTO `address_areas` VALUES (1721, '420529', '五峰土家族自治县', '420500');
INSERT INTO `address_areas` VALUES (1722, '420581', '宜都市', '420500');
INSERT INTO `address_areas` VALUES (1723, '420582', '当阳市', '420500');
INSERT INTO `address_areas` VALUES (1724, '420583', '枝江市', '420500');
INSERT INTO `address_areas` VALUES (1725, '420601', '市辖区', '420600');
INSERT INTO `address_areas` VALUES (1726, '420602', '襄城区', '420600');
INSERT INTO `address_areas` VALUES (1727, '420606', '樊城区', '420600');
INSERT INTO `address_areas` VALUES (1728, '420607', '襄阳区', '420600');
INSERT INTO `address_areas` VALUES (1729, '420624', '南漳县', '420600');
INSERT INTO `address_areas` VALUES (1730, '420625', '谷城县', '420600');
INSERT INTO `address_areas` VALUES (1731, '420626', '保康县', '420600');
INSERT INTO `address_areas` VALUES (1732, '420682', '老河口市', '420600');
INSERT INTO `address_areas` VALUES (1733, '420683', '枣阳市', '420600');
INSERT INTO `address_areas` VALUES (1734, '420684', '宜城市', '420600');
INSERT INTO `address_areas` VALUES (1735, '420701', '市辖区', '420700');
INSERT INTO `address_areas` VALUES (1736, '420702', '梁子湖区', '420700');
INSERT INTO `address_areas` VALUES (1737, '420703', '华容区', '420700');
INSERT INTO `address_areas` VALUES (1738, '420704', '鄂城区', '420700');
INSERT INTO `address_areas` VALUES (1739, '420801', '市辖区', '420800');
INSERT INTO `address_areas` VALUES (1740, '420802', '东宝区', '420800');
INSERT INTO `address_areas` VALUES (1741, '420804', '掇刀区', '420800');
INSERT INTO `address_areas` VALUES (1742, '420821', '京山县', '420800');
INSERT INTO `address_areas` VALUES (1743, '420822', '沙洋县', '420800');
INSERT INTO `address_areas` VALUES (1744, '420881', '钟祥市', '420800');
INSERT INTO `address_areas` VALUES (1745, '420901', '市辖区', '420900');
INSERT INTO `address_areas` VALUES (1746, '420902', '孝南区', '420900');
INSERT INTO `address_areas` VALUES (1747, '420921', '孝昌县', '420900');
INSERT INTO `address_areas` VALUES (1748, '420922', '大悟县', '420900');
INSERT INTO `address_areas` VALUES (1749, '420923', '云梦县', '420900');
INSERT INTO `address_areas` VALUES (1750, '420981', '应城市', '420900');
INSERT INTO `address_areas` VALUES (1751, '420982', '安陆市', '420900');
INSERT INTO `address_areas` VALUES (1752, '420984', '汉川市', '420900');
INSERT INTO `address_areas` VALUES (1753, '421001', '市辖区', '421000');
INSERT INTO `address_areas` VALUES (1754, '421002', '沙市区', '421000');
INSERT INTO `address_areas` VALUES (1755, '421003', '荆州区', '421000');
INSERT INTO `address_areas` VALUES (1756, '421022', '公安县', '421000');
INSERT INTO `address_areas` VALUES (1757, '421023', '监利县', '421000');
INSERT INTO `address_areas` VALUES (1758, '421024', '江陵县', '421000');
INSERT INTO `address_areas` VALUES (1759, '421081', '石首市', '421000');
INSERT INTO `address_areas` VALUES (1760, '421083', '洪湖市', '421000');
INSERT INTO `address_areas` VALUES (1761, '421087', '松滋市', '421000');
INSERT INTO `address_areas` VALUES (1762, '421101', '市辖区', '421100');
INSERT INTO `address_areas` VALUES (1763, '421102', '黄州区', '421100');
INSERT INTO `address_areas` VALUES (1764, '421121', '团风县', '421100');
INSERT INTO `address_areas` VALUES (1765, '421122', '红安县', '421100');
INSERT INTO `address_areas` VALUES (1766, '421123', '罗田县', '421100');
INSERT INTO `address_areas` VALUES (1767, '421124', '英山县', '421100');
INSERT INTO `address_areas` VALUES (1768, '421125', '浠水县', '421100');
INSERT INTO `address_areas` VALUES (1769, '421126', '蕲春县', '421100');
INSERT INTO `address_areas` VALUES (1770, '421127', '黄梅县', '421100');
INSERT INTO `address_areas` VALUES (1771, '421181', '麻城市', '421100');
INSERT INTO `address_areas` VALUES (1772, '421182', '武穴市', '421100');
INSERT INTO `address_areas` VALUES (1773, '421201', '市辖区', '421200');
INSERT INTO `address_areas` VALUES (1774, '421202', '咸安区', '421200');
INSERT INTO `address_areas` VALUES (1775, '421221', '嘉鱼县', '421200');
INSERT INTO `address_areas` VALUES (1776, '421222', '通城县', '421200');
INSERT INTO `address_areas` VALUES (1777, '421223', '崇阳县', '421200');
INSERT INTO `address_areas` VALUES (1778, '421224', '通山县', '421200');
INSERT INTO `address_areas` VALUES (1779, '421281', '赤壁市', '421200');
INSERT INTO `address_areas` VALUES (1780, '421301', '市辖区', '421300');
INSERT INTO `address_areas` VALUES (1781, '421302', '曾都区', '421300');
INSERT INTO `address_areas` VALUES (1782, '421381', '广水市', '421300');
INSERT INTO `address_areas` VALUES (1783, '422801', '恩施市', '422800');
INSERT INTO `address_areas` VALUES (1784, '422802', '利川市', '422800');
INSERT INTO `address_areas` VALUES (1785, '422822', '建始县', '422800');
INSERT INTO `address_areas` VALUES (1786, '422823', '巴东县', '422800');
INSERT INTO `address_areas` VALUES (1787, '422825', '宣恩县', '422800');
INSERT INTO `address_areas` VALUES (1788, '422826', '咸丰县', '422800');
INSERT INTO `address_areas` VALUES (1789, '422827', '来凤县', '422800');
INSERT INTO `address_areas` VALUES (1790, '422828', '鹤峰县', '422800');
INSERT INTO `address_areas` VALUES (1791, '429004', '仙桃市', '429000');
INSERT INTO `address_areas` VALUES (1792, '429005', '潜江市', '429000');
INSERT INTO `address_areas` VALUES (1793, '429006', '天门市', '429000');
INSERT INTO `address_areas` VALUES (1794, '429021', '神农架林区', '429000');
INSERT INTO `address_areas` VALUES (1795, '430101', '市辖区', '430100');
INSERT INTO `address_areas` VALUES (1796, '430102', '芙蓉区', '430100');
INSERT INTO `address_areas` VALUES (1797, '430103', '天心区', '430100');
INSERT INTO `address_areas` VALUES (1798, '430104', '岳麓区', '430100');
INSERT INTO `address_areas` VALUES (1799, '430105', '开福区', '430100');
INSERT INTO `address_areas` VALUES (1800, '430111', '雨花区', '430100');
INSERT INTO `address_areas` VALUES (1801, '430121', '长沙县', '430100');
INSERT INTO `address_areas` VALUES (1802, '430122', '望城县', '430100');
INSERT INTO `address_areas` VALUES (1803, '430124', '宁乡县', '430100');
INSERT INTO `address_areas` VALUES (1804, '430181', '浏阳市', '430100');
INSERT INTO `address_areas` VALUES (1805, '430201', '市辖区', '430200');
INSERT INTO `address_areas` VALUES (1806, '430202', '荷塘区', '430200');
INSERT INTO `address_areas` VALUES (1807, '430203', '芦淞区', '430200');
INSERT INTO `address_areas` VALUES (1808, '430204', '石峰区', '430200');
INSERT INTO `address_areas` VALUES (1809, '430211', '天元区', '430200');
INSERT INTO `address_areas` VALUES (1810, '430221', '株洲县', '430200');
INSERT INTO `address_areas` VALUES (1811, '430223', '攸　县', '430200');
INSERT INTO `address_areas` VALUES (1812, '430224', '茶陵县', '430200');
INSERT INTO `address_areas` VALUES (1813, '430225', '炎陵县', '430200');
INSERT INTO `address_areas` VALUES (1814, '430281', '醴陵市', '430200');
INSERT INTO `address_areas` VALUES (1815, '430301', '市辖区', '430300');
INSERT INTO `address_areas` VALUES (1816, '430302', '雨湖区', '430300');
INSERT INTO `address_areas` VALUES (1817, '430304', '岳塘区', '430300');
INSERT INTO `address_areas` VALUES (1818, '430321', '湘潭县', '430300');
INSERT INTO `address_areas` VALUES (1819, '430381', '湘乡市', '430300');
INSERT INTO `address_areas` VALUES (1820, '430382', '韶山市', '430300');
INSERT INTO `address_areas` VALUES (1821, '430401', '市辖区', '430400');
INSERT INTO `address_areas` VALUES (1822, '430405', '珠晖区', '430400');
INSERT INTO `address_areas` VALUES (1823, '430406', '雁峰区', '430400');
INSERT INTO `address_areas` VALUES (1824, '430407', '石鼓区', '430400');
INSERT INTO `address_areas` VALUES (1825, '430408', '蒸湘区', '430400');
INSERT INTO `address_areas` VALUES (1826, '430412', '南岳区', '430400');
INSERT INTO `address_areas` VALUES (1827, '430421', '衡阳县', '430400');
INSERT INTO `address_areas` VALUES (1828, '430422', '衡南县', '430400');
INSERT INTO `address_areas` VALUES (1829, '430423', '衡山县', '430400');
INSERT INTO `address_areas` VALUES (1830, '430424', '衡东县', '430400');
INSERT INTO `address_areas` VALUES (1831, '430426', '祁东县', '430400');
INSERT INTO `address_areas` VALUES (1832, '430481', '耒阳市', '430400');
INSERT INTO `address_areas` VALUES (1833, '430482', '常宁市', '430400');
INSERT INTO `address_areas` VALUES (1834, '430501', '市辖区', '430500');
INSERT INTO `address_areas` VALUES (1835, '430502', '双清区', '430500');
INSERT INTO `address_areas` VALUES (1836, '430503', '大祥区', '430500');
INSERT INTO `address_areas` VALUES (1837, '430511', '北塔区', '430500');
INSERT INTO `address_areas` VALUES (1838, '430521', '邵东县', '430500');
INSERT INTO `address_areas` VALUES (1839, '430522', '新邵县', '430500');
INSERT INTO `address_areas` VALUES (1840, '430523', '邵阳县', '430500');
INSERT INTO `address_areas` VALUES (1841, '430524', '隆回县', '430500');
INSERT INTO `address_areas` VALUES (1842, '430525', '洞口县', '430500');
INSERT INTO `address_areas` VALUES (1843, '430527', '绥宁县', '430500');
INSERT INTO `address_areas` VALUES (1844, '430528', '新宁县', '430500');
INSERT INTO `address_areas` VALUES (1845, '430529', '城步苗族自治县', '430500');
INSERT INTO `address_areas` VALUES (1846, '430581', '武冈市', '430500');
INSERT INTO `address_areas` VALUES (1847, '430601', '市辖区', '430600');
INSERT INTO `address_areas` VALUES (1848, '430602', '岳阳楼区', '430600');
INSERT INTO `address_areas` VALUES (1849, '430603', '云溪区', '430600');
INSERT INTO `address_areas` VALUES (1850, '430611', '君山区', '430600');
INSERT INTO `address_areas` VALUES (1851, '430621', '岳阳县', '430600');
INSERT INTO `address_areas` VALUES (1852, '430623', '华容县', '430600');
INSERT INTO `address_areas` VALUES (1853, '430624', '湘阴县', '430600');
INSERT INTO `address_areas` VALUES (1854, '430626', '平江县', '430600');
INSERT INTO `address_areas` VALUES (1855, '430681', '汨罗市', '430600');
INSERT INTO `address_areas` VALUES (1856, '430682', '临湘市', '430600');
INSERT INTO `address_areas` VALUES (1857, '430701', '市辖区', '430700');
INSERT INTO `address_areas` VALUES (1858, '430702', '武陵区', '430700');
INSERT INTO `address_areas` VALUES (1859, '430703', '鼎城区', '430700');
INSERT INTO `address_areas` VALUES (1860, '430721', '安乡县', '430700');
INSERT INTO `address_areas` VALUES (1861, '430722', '汉寿县', '430700');
INSERT INTO `address_areas` VALUES (1862, '430723', '澧　县', '430700');
INSERT INTO `address_areas` VALUES (1863, '430724', '临澧县', '430700');
INSERT INTO `address_areas` VALUES (1864, '430725', '桃源县', '430700');
INSERT INTO `address_areas` VALUES (1865, '430726', '石门县', '430700');
INSERT INTO `address_areas` VALUES (1866, '430781', '津市市', '430700');
INSERT INTO `address_areas` VALUES (1867, '430801', '市辖区', '430800');
INSERT INTO `address_areas` VALUES (1868, '430802', '永定区', '430800');
INSERT INTO `address_areas` VALUES (1869, '430811', '武陵源区', '430800');
INSERT INTO `address_areas` VALUES (1870, '430821', '慈利县', '430800');
INSERT INTO `address_areas` VALUES (1871, '430822', '桑植县', '430800');
INSERT INTO `address_areas` VALUES (1872, '430901', '市辖区', '430900');
INSERT INTO `address_areas` VALUES (1873, '430902', '资阳区', '430900');
INSERT INTO `address_areas` VALUES (1874, '430903', '赫山区', '430900');
INSERT INTO `address_areas` VALUES (1875, '430921', '南　县', '430900');
INSERT INTO `address_areas` VALUES (1876, '430922', '桃江县', '430900');
INSERT INTO `address_areas` VALUES (1877, '430923', '安化县', '430900');
INSERT INTO `address_areas` VALUES (1878, '430981', '沅江市', '430900');
INSERT INTO `address_areas` VALUES (1879, '431001', '市辖区', '431000');
INSERT INTO `address_areas` VALUES (1880, '431002', '北湖区', '431000');
INSERT INTO `address_areas` VALUES (1881, '431003', '苏仙区', '431000');
INSERT INTO `address_areas` VALUES (1882, '431021', '桂阳县', '431000');
INSERT INTO `address_areas` VALUES (1883, '431022', '宜章县', '431000');
INSERT INTO `address_areas` VALUES (1884, '431023', '永兴县', '431000');
INSERT INTO `address_areas` VALUES (1885, '431024', '嘉禾县', '431000');
INSERT INTO `address_areas` VALUES (1886, '431025', '临武县', '431000');
INSERT INTO `address_areas` VALUES (1887, '431026', '汝城县', '431000');
INSERT INTO `address_areas` VALUES (1888, '431027', '桂东县', '431000');
INSERT INTO `address_areas` VALUES (1889, '431028', '安仁县', '431000');
INSERT INTO `address_areas` VALUES (1890, '431081', '资兴市', '431000');
INSERT INTO `address_areas` VALUES (1891, '431101', '市辖区', '431100');
INSERT INTO `address_areas` VALUES (1892, '431102', '芝山区', '431100');
INSERT INTO `address_areas` VALUES (1893, '431103', '冷水滩区', '431100');
INSERT INTO `address_areas` VALUES (1894, '431121', '祁阳县', '431100');
INSERT INTO `address_areas` VALUES (1895, '431122', '东安县', '431100');
INSERT INTO `address_areas` VALUES (1896, '431123', '双牌县', '431100');
INSERT INTO `address_areas` VALUES (1897, '431124', '道　县', '431100');
INSERT INTO `address_areas` VALUES (1898, '431125', '江永县', '431100');
INSERT INTO `address_areas` VALUES (1899, '431126', '宁远县', '431100');
INSERT INTO `address_areas` VALUES (1900, '431127', '蓝山县', '431100');
INSERT INTO `address_areas` VALUES (1901, '431128', '新田县', '431100');
INSERT INTO `address_areas` VALUES (1902, '431129', '江华瑶族自治县', '431100');
INSERT INTO `address_areas` VALUES (1903, '431201', '市辖区', '431200');
INSERT INTO `address_areas` VALUES (1904, '431202', '鹤城区', '431200');
INSERT INTO `address_areas` VALUES (1905, '431221', '中方县', '431200');
INSERT INTO `address_areas` VALUES (1906, '431222', '沅陵县', '431200');
INSERT INTO `address_areas` VALUES (1907, '431223', '辰溪县', '431200');
INSERT INTO `address_areas` VALUES (1908, '431224', '溆浦县', '431200');
INSERT INTO `address_areas` VALUES (1909, '431225', '会同县', '431200');
INSERT INTO `address_areas` VALUES (1910, '431226', '麻阳苗族自治县', '431200');
INSERT INTO `address_areas` VALUES (1911, '431227', '新晃侗族自治县', '431200');
INSERT INTO `address_areas` VALUES (1912, '431228', '芷江侗族自治县', '431200');
INSERT INTO `address_areas` VALUES (1913, '431229', '靖州苗族侗族自治县', '431200');
INSERT INTO `address_areas` VALUES (1914, '431230', '通道侗族自治县', '431200');
INSERT INTO `address_areas` VALUES (1915, '431281', '洪江市', '431200');
INSERT INTO `address_areas` VALUES (1916, '431301', '市辖区', '431300');
INSERT INTO `address_areas` VALUES (1917, '431302', '娄星区', '431300');
INSERT INTO `address_areas` VALUES (1918, '431321', '双峰县', '431300');
INSERT INTO `address_areas` VALUES (1919, '431322', '新化县', '431300');
INSERT INTO `address_areas` VALUES (1920, '431381', '冷水江市', '431300');
INSERT INTO `address_areas` VALUES (1921, '431382', '涟源市', '431300');
INSERT INTO `address_areas` VALUES (1922, '433101', '吉首市', '433100');
INSERT INTO `address_areas` VALUES (1923, '433122', '泸溪县', '433100');
INSERT INTO `address_areas` VALUES (1924, '433123', '凤凰县', '433100');
INSERT INTO `address_areas` VALUES (1925, '433124', '花垣县', '433100');
INSERT INTO `address_areas` VALUES (1926, '433125', '保靖县', '433100');
INSERT INTO `address_areas` VALUES (1927, '433126', '古丈县', '433100');
INSERT INTO `address_areas` VALUES (1928, '433127', '永顺县', '433100');
INSERT INTO `address_areas` VALUES (1929, '433130', '龙山县', '433100');
INSERT INTO `address_areas` VALUES (1930, '440101', '市辖区', '440100');
INSERT INTO `address_areas` VALUES (1931, '440102', '东山区', '440100');
INSERT INTO `address_areas` VALUES (1932, '440103', '荔湾区', '440100');
INSERT INTO `address_areas` VALUES (1933, '440104', '越秀区', '440100');
INSERT INTO `address_areas` VALUES (1934, '440105', '海珠区', '440100');
INSERT INTO `address_areas` VALUES (1935, '440106', '天河区', '440100');
INSERT INTO `address_areas` VALUES (1936, '440107', '芳村区', '440100');
INSERT INTO `address_areas` VALUES (1937, '440111', '白云区', '440100');
INSERT INTO `address_areas` VALUES (1938, '440112', '黄埔区', '440100');
INSERT INTO `address_areas` VALUES (1939, '440113', '番禺区', '440100');
INSERT INTO `address_areas` VALUES (1940, '440114', '花都区', '440100');
INSERT INTO `address_areas` VALUES (1941, '440183', '增城市', '440100');
INSERT INTO `address_areas` VALUES (1942, '440184', '从化市', '440100');
INSERT INTO `address_areas` VALUES (1943, '440201', '市辖区', '440200');
INSERT INTO `address_areas` VALUES (1944, '440203', '武江区', '440200');
INSERT INTO `address_areas` VALUES (1945, '440204', '浈江区', '440200');
INSERT INTO `address_areas` VALUES (1946, '440205', '曲江区', '440200');
INSERT INTO `address_areas` VALUES (1947, '440222', '始兴县', '440200');
INSERT INTO `address_areas` VALUES (1948, '440224', '仁化县', '440200');
INSERT INTO `address_areas` VALUES (1949, '440229', '翁源县', '440200');
INSERT INTO `address_areas` VALUES (1950, '440232', '乳源瑶族自治县', '440200');
INSERT INTO `address_areas` VALUES (1951, '440233', '新丰县', '440200');
INSERT INTO `address_areas` VALUES (1952, '440281', '乐昌市', '440200');
INSERT INTO `address_areas` VALUES (1953, '440282', '南雄市', '440200');
INSERT INTO `address_areas` VALUES (1954, '440301', '市辖区', '440300');
INSERT INTO `address_areas` VALUES (1955, '440303', '罗湖区', '440300');
INSERT INTO `address_areas` VALUES (1956, '440304', '福田区', '440300');
INSERT INTO `address_areas` VALUES (1957, '440305', '南山区', '440300');
INSERT INTO `address_areas` VALUES (1958, '440306', '宝安区', '440300');
INSERT INTO `address_areas` VALUES (1959, '440307', '龙岗区', '440300');
INSERT INTO `address_areas` VALUES (1960, '440308', '盐田区', '440300');
INSERT INTO `address_areas` VALUES (1961, '440401', '市辖区', '440400');
INSERT INTO `address_areas` VALUES (1962, '440402', '香洲区', '440400');
INSERT INTO `address_areas` VALUES (1963, '440403', '斗门区', '440400');
INSERT INTO `address_areas` VALUES (1964, '440404', '金湾区', '440400');
INSERT INTO `address_areas` VALUES (1965, '440501', '市辖区', '440500');
INSERT INTO `address_areas` VALUES (1966, '440507', '龙湖区', '440500');
INSERT INTO `address_areas` VALUES (1967, '440511', '金平区', '440500');
INSERT INTO `address_areas` VALUES (1968, '440512', '濠江区', '440500');
INSERT INTO `address_areas` VALUES (1969, '440513', '潮阳区', '440500');
INSERT INTO `address_areas` VALUES (1970, '440514', '潮南区', '440500');
INSERT INTO `address_areas` VALUES (1971, '440515', '澄海区', '440500');
INSERT INTO `address_areas` VALUES (1972, '440523', '南澳县', '440500');
INSERT INTO `address_areas` VALUES (1973, '440601', '市辖区', '440600');
INSERT INTO `address_areas` VALUES (1974, '440604', '禅城区', '440600');
INSERT INTO `address_areas` VALUES (1975, '440605', '南海区', '440600');
INSERT INTO `address_areas` VALUES (1976, '440606', '顺德区', '440600');
INSERT INTO `address_areas` VALUES (1977, '440607', '三水区', '440600');
INSERT INTO `address_areas` VALUES (1978, '440608', '高明区', '440600');
INSERT INTO `address_areas` VALUES (1979, '440701', '市辖区', '440700');
INSERT INTO `address_areas` VALUES (1980, '440703', '蓬江区', '440700');
INSERT INTO `address_areas` VALUES (1981, '440704', '江海区', '440700');
INSERT INTO `address_areas` VALUES (1982, '440705', '新会区', '440700');
INSERT INTO `address_areas` VALUES (1983, '440781', '台山市', '440700');
INSERT INTO `address_areas` VALUES (1984, '440783', '开平市', '440700');
INSERT INTO `address_areas` VALUES (1985, '440784', '鹤山市', '440700');
INSERT INTO `address_areas` VALUES (1986, '440785', '恩平市', '440700');
INSERT INTO `address_areas` VALUES (1987, '440801', '市辖区', '440800');
INSERT INTO `address_areas` VALUES (1988, '440802', '赤坎区', '440800');
INSERT INTO `address_areas` VALUES (1989, '440803', '霞山区', '440800');
INSERT INTO `address_areas` VALUES (1990, '440804', '坡头区', '440800');
INSERT INTO `address_areas` VALUES (1991, '440811', '麻章区', '440800');
INSERT INTO `address_areas` VALUES (1992, '440823', '遂溪县', '440800');
INSERT INTO `address_areas` VALUES (1993, '440825', '徐闻县', '440800');
INSERT INTO `address_areas` VALUES (1994, '440881', '廉江市', '440800');
INSERT INTO `address_areas` VALUES (1995, '440882', '雷州市', '440800');
INSERT INTO `address_areas` VALUES (1996, '440883', '吴川市', '440800');
INSERT INTO `address_areas` VALUES (1997, '440901', '市辖区', '440900');
INSERT INTO `address_areas` VALUES (1998, '440902', '茂南区', '440900');
INSERT INTO `address_areas` VALUES (1999, '440903', '茂港区', '440900');
INSERT INTO `address_areas` VALUES (2000, '440923', '电白县', '440900');
INSERT INTO `address_areas` VALUES (2001, '440981', '高州市', '440900');
INSERT INTO `address_areas` VALUES (2002, '440982', '化州市', '440900');
INSERT INTO `address_areas` VALUES (2003, '440983', '信宜市', '440900');
INSERT INTO `address_areas` VALUES (2004, '441201', '市辖区', '441200');
INSERT INTO `address_areas` VALUES (2005, '441202', '端州区', '441200');
INSERT INTO `address_areas` VALUES (2006, '441203', '鼎湖区', '441200');
INSERT INTO `address_areas` VALUES (2007, '441223', '广宁县', '441200');
INSERT INTO `address_areas` VALUES (2008, '441224', '怀集县', '441200');
INSERT INTO `address_areas` VALUES (2009, '441225', '封开县', '441200');
INSERT INTO `address_areas` VALUES (2010, '441226', '德庆县', '441200');
INSERT INTO `address_areas` VALUES (2011, '441283', '高要市', '441200');
INSERT INTO `address_areas` VALUES (2012, '441284', '四会市', '441200');
INSERT INTO `address_areas` VALUES (2013, '441301', '市辖区', '441300');
INSERT INTO `address_areas` VALUES (2014, '441302', '惠城区', '441300');
INSERT INTO `address_areas` VALUES (2015, '441303', '惠阳区', '441300');
INSERT INTO `address_areas` VALUES (2016, '441322', '博罗县', '441300');
INSERT INTO `address_areas` VALUES (2017, '441323', '惠东县', '441300');
INSERT INTO `address_areas` VALUES (2018, '441324', '龙门县', '441300');
INSERT INTO `address_areas` VALUES (2019, '441401', '市辖区', '441400');
INSERT INTO `address_areas` VALUES (2020, '441402', '梅江区', '441400');
INSERT INTO `address_areas` VALUES (2021, '441421', '梅　县', '441400');
INSERT INTO `address_areas` VALUES (2022, '441422', '大埔县', '441400');
INSERT INTO `address_areas` VALUES (2023, '441423', '丰顺县', '441400');
INSERT INTO `address_areas` VALUES (2024, '441424', '五华县', '441400');
INSERT INTO `address_areas` VALUES (2025, '441426', '平远县', '441400');
INSERT INTO `address_areas` VALUES (2026, '441427', '蕉岭县', '441400');
INSERT INTO `address_areas` VALUES (2027, '441481', '兴宁市', '441400');
INSERT INTO `address_areas` VALUES (2028, '441501', '市辖区', '441500');
INSERT INTO `address_areas` VALUES (2029, '441502', '城　区', '441500');
INSERT INTO `address_areas` VALUES (2030, '441521', '海丰县', '441500');
INSERT INTO `address_areas` VALUES (2031, '441523', '陆河县', '441500');
INSERT INTO `address_areas` VALUES (2032, '441581', '陆丰市', '441500');
INSERT INTO `address_areas` VALUES (2033, '441601', '市辖区', '441600');
INSERT INTO `address_areas` VALUES (2034, '441602', '源城区', '441600');
INSERT INTO `address_areas` VALUES (2035, '441621', '紫金县', '441600');
INSERT INTO `address_areas` VALUES (2036, '441622', '龙川县', '441600');
INSERT INTO `address_areas` VALUES (2037, '441623', '连平县', '441600');
INSERT INTO `address_areas` VALUES (2038, '441624', '和平县', '441600');
INSERT INTO `address_areas` VALUES (2039, '441625', '东源县', '441600');
INSERT INTO `address_areas` VALUES (2040, '441701', '市辖区', '441700');
INSERT INTO `address_areas` VALUES (2041, '441702', '江城区', '441700');
INSERT INTO `address_areas` VALUES (2042, '441721', '阳西县', '441700');
INSERT INTO `address_areas` VALUES (2043, '441723', '阳东县', '441700');
INSERT INTO `address_areas` VALUES (2044, '441781', '阳春市', '441700');
INSERT INTO `address_areas` VALUES (2045, '441801', '市辖区', '441800');
INSERT INTO `address_areas` VALUES (2046, '441802', '清城区', '441800');
INSERT INTO `address_areas` VALUES (2047, '441821', '佛冈县', '441800');
INSERT INTO `address_areas` VALUES (2048, '441823', '阳山县', '441800');
INSERT INTO `address_areas` VALUES (2049, '441825', '连山壮族瑶族自治县', '441800');
INSERT INTO `address_areas` VALUES (2050, '441826', '连南瑶族自治县', '441800');
INSERT INTO `address_areas` VALUES (2051, '441827', '清新县', '441800');
INSERT INTO `address_areas` VALUES (2052, '441881', '英德市', '441800');
INSERT INTO `address_areas` VALUES (2053, '441882', '连州市', '441800');
INSERT INTO `address_areas` VALUES (2054, '445101', '市辖区', '445100');
INSERT INTO `address_areas` VALUES (2055, '445102', '湘桥区', '445100');
INSERT INTO `address_areas` VALUES (2056, '445121', '潮安县', '445100');
INSERT INTO `address_areas` VALUES (2057, '445122', '饶平县', '445100');
INSERT INTO `address_areas` VALUES (2058, '445201', '市辖区', '445200');
INSERT INTO `address_areas` VALUES (2059, '445202', '榕城区', '445200');
INSERT INTO `address_areas` VALUES (2060, '445221', '揭东县', '445200');
INSERT INTO `address_areas` VALUES (2061, '445222', '揭西县', '445200');
INSERT INTO `address_areas` VALUES (2062, '445224', '惠来县', '445200');
INSERT INTO `address_areas` VALUES (2063, '445281', '普宁市', '445200');
INSERT INTO `address_areas` VALUES (2064, '445301', '市辖区', '445300');
INSERT INTO `address_areas` VALUES (2065, '445302', '云城区', '445300');
INSERT INTO `address_areas` VALUES (2066, '445321', '新兴县', '445300');
INSERT INTO `address_areas` VALUES (2067, '445322', '郁南县', '445300');
INSERT INTO `address_areas` VALUES (2068, '445323', '云安县', '445300');
INSERT INTO `address_areas` VALUES (2069, '445381', '罗定市', '445300');
INSERT INTO `address_areas` VALUES (2070, '450101', '市辖区', '450100');
INSERT INTO `address_areas` VALUES (2071, '450102', '兴宁区', '450100');
INSERT INTO `address_areas` VALUES (2072, '450103', '青秀区', '450100');
INSERT INTO `address_areas` VALUES (2073, '450105', '江南区', '450100');
INSERT INTO `address_areas` VALUES (2074, '450107', '西乡塘区', '450100');
INSERT INTO `address_areas` VALUES (2075, '450108', '良庆区', '450100');
INSERT INTO `address_areas` VALUES (2076, '450109', '邕宁区', '450100');
INSERT INTO `address_areas` VALUES (2077, '450122', '武鸣县', '450100');
INSERT INTO `address_areas` VALUES (2078, '450123', '隆安县', '450100');
INSERT INTO `address_areas` VALUES (2079, '450124', '马山县', '450100');
INSERT INTO `address_areas` VALUES (2080, '450125', '上林县', '450100');
INSERT INTO `address_areas` VALUES (2081, '450126', '宾阳县', '450100');
INSERT INTO `address_areas` VALUES (2082, '450127', '横　县', '450100');
INSERT INTO `address_areas` VALUES (2083, '450201', '市辖区', '450200');
INSERT INTO `address_areas` VALUES (2084, '450202', '城中区', '450200');
INSERT INTO `address_areas` VALUES (2085, '450203', '鱼峰区', '450200');
INSERT INTO `address_areas` VALUES (2086, '450204', '柳南区', '450200');
INSERT INTO `address_areas` VALUES (2087, '450205', '柳北区', '450200');
INSERT INTO `address_areas` VALUES (2088, '450221', '柳江县', '450200');
INSERT INTO `address_areas` VALUES (2089, '450222', '柳城县', '450200');
INSERT INTO `address_areas` VALUES (2090, '450223', '鹿寨县', '450200');
INSERT INTO `address_areas` VALUES (2091, '450224', '融安县', '450200');
INSERT INTO `address_areas` VALUES (2092, '450225', '融水苗族自治县', '450200');
INSERT INTO `address_areas` VALUES (2093, '450226', '三江侗族自治县', '450200');
INSERT INTO `address_areas` VALUES (2094, '450301', '市辖区', '450300');
INSERT INTO `address_areas` VALUES (2095, '450302', '秀峰区', '450300');
INSERT INTO `address_areas` VALUES (2096, '450303', '叠彩区', '450300');
INSERT INTO `address_areas` VALUES (2097, '450304', '象山区', '450300');
INSERT INTO `address_areas` VALUES (2098, '450305', '七星区', '450300');
INSERT INTO `address_areas` VALUES (2099, '450311', '雁山区', '450300');
INSERT INTO `address_areas` VALUES (2100, '450321', '阳朔县', '450300');
INSERT INTO `address_areas` VALUES (2101, '450322', '临桂县', '450300');
INSERT INTO `address_areas` VALUES (2102, '450323', '灵川县', '450300');
INSERT INTO `address_areas` VALUES (2103, '450324', '全州县', '450300');
INSERT INTO `address_areas` VALUES (2104, '450325', '兴安县', '450300');
INSERT INTO `address_areas` VALUES (2105, '450326', '永福县', '450300');
INSERT INTO `address_areas` VALUES (2106, '450327', '灌阳县', '450300');
INSERT INTO `address_areas` VALUES (2107, '450328', '龙胜各族自治县', '450300');
INSERT INTO `address_areas` VALUES (2108, '450329', '资源县', '450300');
INSERT INTO `address_areas` VALUES (2109, '450330', '平乐县', '450300');
INSERT INTO `address_areas` VALUES (2110, '450331', '荔蒲县', '450300');
INSERT INTO `address_areas` VALUES (2111, '450332', '恭城瑶族自治县', '450300');
INSERT INTO `address_areas` VALUES (2112, '450401', '市辖区', '450400');
INSERT INTO `address_areas` VALUES (2113, '450403', '万秀区', '450400');
INSERT INTO `address_areas` VALUES (2114, '450404', '蝶山区', '450400');
INSERT INTO `address_areas` VALUES (2115, '450405', '长洲区', '450400');
INSERT INTO `address_areas` VALUES (2116, '450421', '苍梧县', '450400');
INSERT INTO `address_areas` VALUES (2117, '450422', '藤　县', '450400');
INSERT INTO `address_areas` VALUES (2118, '450423', '蒙山县', '450400');
INSERT INTO `address_areas` VALUES (2119, '450481', '岑溪市', '450400');
INSERT INTO `address_areas` VALUES (2120, '450501', '市辖区', '450500');
INSERT INTO `address_areas` VALUES (2121, '450502', '海城区', '450500');
INSERT INTO `address_areas` VALUES (2122, '450503', '银海区', '450500');
INSERT INTO `address_areas` VALUES (2123, '450512', '铁山港区', '450500');
INSERT INTO `address_areas` VALUES (2124, '450521', '合浦县', '450500');
INSERT INTO `address_areas` VALUES (2125, '450601', '市辖区', '450600');
INSERT INTO `address_areas` VALUES (2126, '450602', '港口区', '450600');
INSERT INTO `address_areas` VALUES (2127, '450603', '防城区', '450600');
INSERT INTO `address_areas` VALUES (2128, '450621', '上思县', '450600');
INSERT INTO `address_areas` VALUES (2129, '450681', '东兴市', '450600');
INSERT INTO `address_areas` VALUES (2130, '450701', '市辖区', '450700');
INSERT INTO `address_areas` VALUES (2131, '450702', '钦南区', '450700');
INSERT INTO `address_areas` VALUES (2132, '450703', '钦北区', '450700');
INSERT INTO `address_areas` VALUES (2133, '450721', '灵山县', '450700');
INSERT INTO `address_areas` VALUES (2134, '450722', '浦北县', '450700');
INSERT INTO `address_areas` VALUES (2135, '450801', '市辖区', '450800');
INSERT INTO `address_areas` VALUES (2136, '450802', '港北区', '450800');
INSERT INTO `address_areas` VALUES (2137, '450803', '港南区', '450800');
INSERT INTO `address_areas` VALUES (2138, '450804', '覃塘区', '450800');
INSERT INTO `address_areas` VALUES (2139, '450821', '平南县', '450800');
INSERT INTO `address_areas` VALUES (2140, '450881', '桂平市', '450800');
INSERT INTO `address_areas` VALUES (2141, '450901', '市辖区', '450900');
INSERT INTO `address_areas` VALUES (2142, '450902', '玉州区', '450900');
INSERT INTO `address_areas` VALUES (2143, '450921', '容　县', '450900');
INSERT INTO `address_areas` VALUES (2144, '450922', '陆川县', '450900');
INSERT INTO `address_areas` VALUES (2145, '450923', '博白县', '450900');
INSERT INTO `address_areas` VALUES (2146, '450924', '兴业县', '450900');
INSERT INTO `address_areas` VALUES (2147, '450981', '北流市', '450900');
INSERT INTO `address_areas` VALUES (2148, '451001', '市辖区', '451000');
INSERT INTO `address_areas` VALUES (2149, '451002', '右江区', '451000');
INSERT INTO `address_areas` VALUES (2150, '451021', '田阳县', '451000');
INSERT INTO `address_areas` VALUES (2151, '451022', '田东县', '451000');
INSERT INTO `address_areas` VALUES (2152, '451023', '平果县', '451000');
INSERT INTO `address_areas` VALUES (2153, '451024', '德保县', '451000');
INSERT INTO `address_areas` VALUES (2154, '451025', '靖西县', '451000');
INSERT INTO `address_areas` VALUES (2155, '451026', '那坡县', '451000');
INSERT INTO `address_areas` VALUES (2156, '451027', '凌云县', '451000');
INSERT INTO `address_areas` VALUES (2157, '451028', '乐业县', '451000');
INSERT INTO `address_areas` VALUES (2158, '451029', '田林县', '451000');
INSERT INTO `address_areas` VALUES (2159, '451030', '西林县', '451000');
INSERT INTO `address_areas` VALUES (2160, '451031', '隆林各族自治县', '451000');
INSERT INTO `address_areas` VALUES (2161, '451101', '市辖区', '451100');
INSERT INTO `address_areas` VALUES (2162, '451102', '八步区', '451100');
INSERT INTO `address_areas` VALUES (2163, '451121', '昭平县', '451100');
INSERT INTO `address_areas` VALUES (2164, '451122', '钟山县', '451100');
INSERT INTO `address_areas` VALUES (2165, '451123', '富川瑶族自治县', '451100');
INSERT INTO `address_areas` VALUES (2166, '451201', '市辖区', '451200');
INSERT INTO `address_areas` VALUES (2167, '451202', '金城江区', '451200');
INSERT INTO `address_areas` VALUES (2168, '451221', '南丹县', '451200');
INSERT INTO `address_areas` VALUES (2169, '451222', '天峨县', '451200');
INSERT INTO `address_areas` VALUES (2170, '451223', '凤山县', '451200');
INSERT INTO `address_areas` VALUES (2171, '451224', '东兰县', '451200');
INSERT INTO `address_areas` VALUES (2172, '451225', '罗城仫佬族自治县', '451200');
INSERT INTO `address_areas` VALUES (2173, '451226', '环江毛南族自治县', '451200');
INSERT INTO `address_areas` VALUES (2174, '451227', '巴马瑶族自治县', '451200');
INSERT INTO `address_areas` VALUES (2175, '451228', '都安瑶族自治县', '451200');
INSERT INTO `address_areas` VALUES (2176, '451229', '大化瑶族自治县', '451200');
INSERT INTO `address_areas` VALUES (2177, '451281', '宜州市', '451200');
INSERT INTO `address_areas` VALUES (2178, '451301', '市辖区', '451300');
INSERT INTO `address_areas` VALUES (2179, '451302', '兴宾区', '451300');
INSERT INTO `address_areas` VALUES (2180, '451321', '忻城县', '451300');
INSERT INTO `address_areas` VALUES (2181, '451322', '象州县', '451300');
INSERT INTO `address_areas` VALUES (2182, '451323', '武宣县', '451300');
INSERT INTO `address_areas` VALUES (2183, '451324', '金秀瑶族自治县', '451300');
INSERT INTO `address_areas` VALUES (2184, '451381', '合山市', '451300');
INSERT INTO `address_areas` VALUES (2185, '451401', '市辖区', '451400');
INSERT INTO `address_areas` VALUES (2186, '451402', '江洲区', '451400');
INSERT INTO `address_areas` VALUES (2187, '451421', '扶绥县', '451400');
INSERT INTO `address_areas` VALUES (2188, '451422', '宁明县', '451400');
INSERT INTO `address_areas` VALUES (2189, '451423', '龙州县', '451400');
INSERT INTO `address_areas` VALUES (2190, '451424', '大新县', '451400');
INSERT INTO `address_areas` VALUES (2191, '451425', '天等县', '451400');
INSERT INTO `address_areas` VALUES (2192, '451481', '凭祥市', '451400');
INSERT INTO `address_areas` VALUES (2193, '460101', '市辖区', '460100');
INSERT INTO `address_areas` VALUES (2194, '460105', '秀英区', '460100');
INSERT INTO `address_areas` VALUES (2195, '460106', '龙华区', '460100');
INSERT INTO `address_areas` VALUES (2196, '460107', '琼山区', '460100');
INSERT INTO `address_areas` VALUES (2197, '460108', '美兰区', '460100');
INSERT INTO `address_areas` VALUES (2198, '460201', '市辖区', '460200');
INSERT INTO `address_areas` VALUES (2199, '469001', '五指山市', '469000');
INSERT INTO `address_areas` VALUES (2200, '469002', '琼海市', '469000');
INSERT INTO `address_areas` VALUES (2201, '469003', '儋州市', '469000');
INSERT INTO `address_areas` VALUES (2202, '469005', '文昌市', '469000');
INSERT INTO `address_areas` VALUES (2203, '469006', '万宁市', '469000');
INSERT INTO `address_areas` VALUES (2204, '469007', '东方市', '469000');
INSERT INTO `address_areas` VALUES (2205, '469025', '定安县', '469000');
INSERT INTO `address_areas` VALUES (2206, '469026', '屯昌县', '469000');
INSERT INTO `address_areas` VALUES (2207, '469027', '澄迈县', '469000');
INSERT INTO `address_areas` VALUES (2208, '469028', '临高县', '469000');
INSERT INTO `address_areas` VALUES (2209, '469030', '白沙黎族自治县', '469000');
INSERT INTO `address_areas` VALUES (2210, '469031', '昌江黎族自治县', '469000');
INSERT INTO `address_areas` VALUES (2211, '469033', '乐东黎族自治县', '469000');
INSERT INTO `address_areas` VALUES (2212, '469034', '陵水黎族自治县', '469000');
INSERT INTO `address_areas` VALUES (2213, '469035', '保亭黎族苗族自治县', '469000');
INSERT INTO `address_areas` VALUES (2214, '469036', '琼中黎族苗族自治县', '469000');
INSERT INTO `address_areas` VALUES (2215, '469037', '西沙群岛', '469000');
INSERT INTO `address_areas` VALUES (2216, '469038', '南沙群岛', '469000');
INSERT INTO `address_areas` VALUES (2217, '469039', '中沙群岛的岛礁及其海域', '469000');
INSERT INTO `address_areas` VALUES (2218, '500101', '万州区', '500100');
INSERT INTO `address_areas` VALUES (2219, '500102', '涪陵区', '500100');
INSERT INTO `address_areas` VALUES (2220, '500103', '渝中区', '500100');
INSERT INTO `address_areas` VALUES (2221, '500104', '大渡口区', '500100');
INSERT INTO `address_areas` VALUES (2222, '500105', '江北区', '500100');
INSERT INTO `address_areas` VALUES (2223, '500106', '沙坪坝区', '500100');
INSERT INTO `address_areas` VALUES (2224, '500107', '九龙坡区', '500100');
INSERT INTO `address_areas` VALUES (2225, '500108', '南岸区', '500100');
INSERT INTO `address_areas` VALUES (2226, '500109', '北碚区', '500100');
INSERT INTO `address_areas` VALUES (2227, '500110', '万盛区', '500100');
INSERT INTO `address_areas` VALUES (2228, '500111', '双桥区', '500100');
INSERT INTO `address_areas` VALUES (2229, '500112', '渝北区', '500100');
INSERT INTO `address_areas` VALUES (2230, '500113', '巴南区', '500100');
INSERT INTO `address_areas` VALUES (2231, '500114', '黔江区', '500100');
INSERT INTO `address_areas` VALUES (2232, '500115', '长寿区', '500100');
INSERT INTO `address_areas` VALUES (2233, '500222', '綦江县', '500100');
INSERT INTO `address_areas` VALUES (2234, '500223', '潼南县', '500100');
INSERT INTO `address_areas` VALUES (2235, '500224', '铜梁县', '500100');
INSERT INTO `address_areas` VALUES (2236, '500225', '大足县', '500100');
INSERT INTO `address_areas` VALUES (2237, '500226', '荣昌县', '500100');
INSERT INTO `address_areas` VALUES (2238, '500227', '璧山县', '500100');
INSERT INTO `address_areas` VALUES (2239, '500228', '梁平县', '500100');
INSERT INTO `address_areas` VALUES (2240, '500229', '城口县', '500100');
INSERT INTO `address_areas` VALUES (2241, '500230', '丰都县', '500100');
INSERT INTO `address_areas` VALUES (2242, '500231', '垫江县', '500100');
INSERT INTO `address_areas` VALUES (2243, '500232', '武隆县', '500100');
INSERT INTO `address_areas` VALUES (2244, '500233', '忠　县', '500100');
INSERT INTO `address_areas` VALUES (2245, '500234', '开　县', '500100');
INSERT INTO `address_areas` VALUES (2246, '500235', '云阳县', '500100');
INSERT INTO `address_areas` VALUES (2247, '500236', '奉节县', '500100');
INSERT INTO `address_areas` VALUES (2248, '500237', '巫山县', '500100');
INSERT INTO `address_areas` VALUES (2249, '500238', '巫溪县', '500100');
INSERT INTO `address_areas` VALUES (2250, '500240', '石柱土家族自治县', '500100');
INSERT INTO `address_areas` VALUES (2251, '500241', '秀山土家族苗族自治县', '500100');
INSERT INTO `address_areas` VALUES (2252, '500242', '酉阳土家族苗族自治县', '500100');
INSERT INTO `address_areas` VALUES (2253, '500243', '彭水苗族土家族自治县', '500100');
INSERT INTO `address_areas` VALUES (2254, '500381', '江津市', '500100');
INSERT INTO `address_areas` VALUES (2255, '500382', '合川市', '500100');
INSERT INTO `address_areas` VALUES (2256, '500383', '永川市', '500100');
INSERT INTO `address_areas` VALUES (2257, '500384', '南川市', '500100');
INSERT INTO `address_areas` VALUES (2258, '510101', '市辖区', '510100');
INSERT INTO `address_areas` VALUES (2259, '510104', '锦江区', '510100');
INSERT INTO `address_areas` VALUES (2260, '510105', '青羊区', '510100');
INSERT INTO `address_areas` VALUES (2261, '510106', '金牛区', '510100');
INSERT INTO `address_areas` VALUES (2262, '510107', '武侯区', '510100');
INSERT INTO `address_areas` VALUES (2263, '510108', '成华区', '510100');
INSERT INTO `address_areas` VALUES (2264, '510112', '龙泉驿区', '510100');
INSERT INTO `address_areas` VALUES (2265, '510113', '青白江区', '510100');
INSERT INTO `address_areas` VALUES (2266, '510114', '新都区', '510100');
INSERT INTO `address_areas` VALUES (2267, '510115', '温江区', '510100');
INSERT INTO `address_areas` VALUES (2268, '510121', '金堂县', '510100');
INSERT INTO `address_areas` VALUES (2269, '510122', '双流县', '510100');
INSERT INTO `address_areas` VALUES (2270, '510124', '郫　县', '510100');
INSERT INTO `address_areas` VALUES (2271, '510129', '大邑县', '510100');
INSERT INTO `address_areas` VALUES (2272, '510131', '蒲江县', '510100');
INSERT INTO `address_areas` VALUES (2273, '510132', '新津县', '510100');
INSERT INTO `address_areas` VALUES (2274, '510181', '都江堰市', '510100');
INSERT INTO `address_areas` VALUES (2275, '510182', '彭州市', '510100');
INSERT INTO `address_areas` VALUES (2276, '510183', '邛崃市', '510100');
INSERT INTO `address_areas` VALUES (2277, '510184', '崇州市', '510100');
INSERT INTO `address_areas` VALUES (2278, '510301', '市辖区', '510300');
INSERT INTO `address_areas` VALUES (2279, '510302', '自流井区', '510300');
INSERT INTO `address_areas` VALUES (2280, '510303', '贡井区', '510300');
INSERT INTO `address_areas` VALUES (2281, '510304', '大安区', '510300');
INSERT INTO `address_areas` VALUES (2282, '510311', '沿滩区', '510300');
INSERT INTO `address_areas` VALUES (2283, '510321', '荣　县', '510300');
INSERT INTO `address_areas` VALUES (2284, '510322', '富顺县', '510300');
INSERT INTO `address_areas` VALUES (2285, '510401', '市辖区', '510400');
INSERT INTO `address_areas` VALUES (2286, '510402', '东　区', '510400');
INSERT INTO `address_areas` VALUES (2287, '510403', '西　区', '510400');
INSERT INTO `address_areas` VALUES (2288, '510411', '仁和区', '510400');
INSERT INTO `address_areas` VALUES (2289, '510421', '米易县', '510400');
INSERT INTO `address_areas` VALUES (2290, '510422', '盐边县', '510400');
INSERT INTO `address_areas` VALUES (2291, '510501', '市辖区', '510500');
INSERT INTO `address_areas` VALUES (2292, '510502', '江阳区', '510500');
INSERT INTO `address_areas` VALUES (2293, '510503', '纳溪区', '510500');
INSERT INTO `address_areas` VALUES (2294, '510504', '龙马潭区', '510500');
INSERT INTO `address_areas` VALUES (2295, '510521', '泸　县', '510500');
INSERT INTO `address_areas` VALUES (2296, '510522', '合江县', '510500');
INSERT INTO `address_areas` VALUES (2297, '510524', '叙永县', '510500');
INSERT INTO `address_areas` VALUES (2298, '510525', '古蔺县', '510500');
INSERT INTO `address_areas` VALUES (2299, '510601', '市辖区', '510600');
INSERT INTO `address_areas` VALUES (2300, '510603', '旌阳区', '510600');
INSERT INTO `address_areas` VALUES (2301, '510623', '中江县', '510600');
INSERT INTO `address_areas` VALUES (2302, '510626', '罗江县', '510600');
INSERT INTO `address_areas` VALUES (2303, '510681', '广汉市', '510600');
INSERT INTO `address_areas` VALUES (2304, '510682', '什邡市', '510600');
INSERT INTO `address_areas` VALUES (2305, '510683', '绵竹市', '510600');
INSERT INTO `address_areas` VALUES (2306, '510701', '市辖区', '510700');
INSERT INTO `address_areas` VALUES (2307, '510703', '涪城区', '510700');
INSERT INTO `address_areas` VALUES (2308, '510704', '游仙区', '510700');
INSERT INTO `address_areas` VALUES (2309, '510722', '三台县', '510700');
INSERT INTO `address_areas` VALUES (2310, '510723', '盐亭县', '510700');
INSERT INTO `address_areas` VALUES (2311, '510724', '安　县', '510700');
INSERT INTO `address_areas` VALUES (2312, '510725', '梓潼县', '510700');
INSERT INTO `address_areas` VALUES (2313, '510726', '北川羌族自治县', '510700');
INSERT INTO `address_areas` VALUES (2314, '510727', '平武县', '510700');
INSERT INTO `address_areas` VALUES (2315, '510781', '江油市', '510700');
INSERT INTO `address_areas` VALUES (2316, '510801', '市辖区', '510800');
INSERT INTO `address_areas` VALUES (2317, '510802', '市中区', '510800');
INSERT INTO `address_areas` VALUES (2318, '510811', '元坝区', '510800');
INSERT INTO `address_areas` VALUES (2319, '510812', '朝天区', '510800');
INSERT INTO `address_areas` VALUES (2320, '510821', '旺苍县', '510800');
INSERT INTO `address_areas` VALUES (2321, '510822', '青川县', '510800');
INSERT INTO `address_areas` VALUES (2322, '510823', '剑阁县', '510800');
INSERT INTO `address_areas` VALUES (2323, '510824', '苍溪县', '510800');
INSERT INTO `address_areas` VALUES (2324, '510901', '市辖区', '510900');
INSERT INTO `address_areas` VALUES (2325, '510903', '船山区', '510900');
INSERT INTO `address_areas` VALUES (2326, '510904', '安居区', '510900');
INSERT INTO `address_areas` VALUES (2327, '510921', '蓬溪县', '510900');
INSERT INTO `address_areas` VALUES (2328, '510922', '射洪县', '510900');
INSERT INTO `address_areas` VALUES (2329, '510923', '大英县', '510900');
INSERT INTO `address_areas` VALUES (2330, '511001', '市辖区', '511000');
INSERT INTO `address_areas` VALUES (2331, '511002', '市中区', '511000');
INSERT INTO `address_areas` VALUES (2332, '511011', '东兴区', '511000');
INSERT INTO `address_areas` VALUES (2333, '511024', '威远县', '511000');
INSERT INTO `address_areas` VALUES (2334, '511025', '资中县', '511000');
INSERT INTO `address_areas` VALUES (2335, '511028', '隆昌县', '511000');
INSERT INTO `address_areas` VALUES (2336, '511101', '市辖区', '511100');
INSERT INTO `address_areas` VALUES (2337, '511102', '市中区', '511100');
INSERT INTO `address_areas` VALUES (2338, '511111', '沙湾区', '511100');
INSERT INTO `address_areas` VALUES (2339, '511112', '五通桥区', '511100');
INSERT INTO `address_areas` VALUES (2340, '511113', '金口河区', '511100');
INSERT INTO `address_areas` VALUES (2341, '511123', '犍为县', '511100');
INSERT INTO `address_areas` VALUES (2342, '511124', '井研县', '511100');
INSERT INTO `address_areas` VALUES (2343, '511126', '夹江县', '511100');
INSERT INTO `address_areas` VALUES (2344, '511129', '沐川县', '511100');
INSERT INTO `address_areas` VALUES (2345, '511132', '峨边彝族自治县', '511100');
INSERT INTO `address_areas` VALUES (2346, '511133', '马边彝族自治县', '511100');
INSERT INTO `address_areas` VALUES (2347, '511181', '峨眉山市', '511100');
INSERT INTO `address_areas` VALUES (2348, '511301', '市辖区', '511300');
INSERT INTO `address_areas` VALUES (2349, '511302', '顺庆区', '511300');
INSERT INTO `address_areas` VALUES (2350, '511303', '高坪区', '511300');
INSERT INTO `address_areas` VALUES (2351, '511304', '嘉陵区', '511300');
INSERT INTO `address_areas` VALUES (2352, '511321', '南部县', '511300');
INSERT INTO `address_areas` VALUES (2353, '511322', '营山县', '511300');
INSERT INTO `address_areas` VALUES (2354, '511323', '蓬安县', '511300');
INSERT INTO `address_areas` VALUES (2355, '511324', '仪陇县', '511300');
INSERT INTO `address_areas` VALUES (2356, '511325', '西充县', '511300');
INSERT INTO `address_areas` VALUES (2357, '511381', '阆中市', '511300');
INSERT INTO `address_areas` VALUES (2358, '511401', '市辖区', '511400');
INSERT INTO `address_areas` VALUES (2359, '511402', '东坡区', '511400');
INSERT INTO `address_areas` VALUES (2360, '511421', '仁寿县', '511400');
INSERT INTO `address_areas` VALUES (2361, '511422', '彭山县', '511400');
INSERT INTO `address_areas` VALUES (2362, '511423', '洪雅县', '511400');
INSERT INTO `address_areas` VALUES (2363, '511424', '丹棱县', '511400');
INSERT INTO `address_areas` VALUES (2364, '511425', '青神县', '511400');
INSERT INTO `address_areas` VALUES (2365, '511501', '市辖区', '511500');
INSERT INTO `address_areas` VALUES (2366, '511502', '翠屏区', '511500');
INSERT INTO `address_areas` VALUES (2367, '511521', '宜宾县', '511500');
INSERT INTO `address_areas` VALUES (2368, '511522', '南溪县', '511500');
INSERT INTO `address_areas` VALUES (2369, '511523', '江安县', '511500');
INSERT INTO `address_areas` VALUES (2370, '511524', '长宁县', '511500');
INSERT INTO `address_areas` VALUES (2371, '511525', '高　县', '511500');
INSERT INTO `address_areas` VALUES (2372, '511526', '珙　县', '511500');
INSERT INTO `address_areas` VALUES (2373, '511527', '筠连县', '511500');
INSERT INTO `address_areas` VALUES (2374, '511528', '兴文县', '511500');
INSERT INTO `address_areas` VALUES (2375, '511529', '屏山县', '511500');
INSERT INTO `address_areas` VALUES (2376, '511601', '市辖区', '511600');
INSERT INTO `address_areas` VALUES (2377, '511602', '广安区', '511600');
INSERT INTO `address_areas` VALUES (2378, '511621', '岳池县', '511600');
INSERT INTO `address_areas` VALUES (2379, '511622', '武胜县', '511600');
INSERT INTO `address_areas` VALUES (2380, '511623', '邻水县', '511600');
INSERT INTO `address_areas` VALUES (2381, '511681', '华莹市', '511600');
INSERT INTO `address_areas` VALUES (2382, '511701', '市辖区', '511700');
INSERT INTO `address_areas` VALUES (2383, '511702', '通川区', '511700');
INSERT INTO `address_areas` VALUES (2384, '511721', '达　县', '511700');
INSERT INTO `address_areas` VALUES (2385, '511722', '宣汉县', '511700');
INSERT INTO `address_areas` VALUES (2386, '511723', '开江县', '511700');
INSERT INTO `address_areas` VALUES (2387, '511724', '大竹县', '511700');
INSERT INTO `address_areas` VALUES (2388, '511725', '渠　县', '511700');
INSERT INTO `address_areas` VALUES (2389, '511781', '万源市', '511700');
INSERT INTO `address_areas` VALUES (2390, '511801', '市辖区', '511800');
INSERT INTO `address_areas` VALUES (2391, '511802', '雨城区', '511800');
INSERT INTO `address_areas` VALUES (2392, '511821', '名山县', '511800');
INSERT INTO `address_areas` VALUES (2393, '511822', '荥经县', '511800');
INSERT INTO `address_areas` VALUES (2394, '511823', '汉源县', '511800');
INSERT INTO `address_areas` VALUES (2395, '511824', '石棉县', '511800');
INSERT INTO `address_areas` VALUES (2396, '511825', '天全县', '511800');
INSERT INTO `address_areas` VALUES (2397, '511826', '芦山县', '511800');
INSERT INTO `address_areas` VALUES (2398, '511827', '宝兴县', '511800');
INSERT INTO `address_areas` VALUES (2399, '511901', '市辖区', '511900');
INSERT INTO `address_areas` VALUES (2400, '511902', '巴州区', '511900');
INSERT INTO `address_areas` VALUES (2401, '511921', '通江县', '511900');
INSERT INTO `address_areas` VALUES (2402, '511922', '南江县', '511900');
INSERT INTO `address_areas` VALUES (2403, '511923', '平昌县', '511900');
INSERT INTO `address_areas` VALUES (2404, '512001', '市辖区', '512000');
INSERT INTO `address_areas` VALUES (2405, '512002', '雁江区', '512000');
INSERT INTO `address_areas` VALUES (2406, '512021', '安岳县', '512000');
INSERT INTO `address_areas` VALUES (2407, '512022', '乐至县', '512000');
INSERT INTO `address_areas` VALUES (2408, '512081', '简阳市', '512000');
INSERT INTO `address_areas` VALUES (2409, '513221', '汶川县', '513200');
INSERT INTO `address_areas` VALUES (2410, '513222', '理　县', '513200');
INSERT INTO `address_areas` VALUES (2411, '513223', '茂　县', '513200');
INSERT INTO `address_areas` VALUES (2412, '513224', '松潘县', '513200');
INSERT INTO `address_areas` VALUES (2413, '513225', '九寨沟县', '513200');
INSERT INTO `address_areas` VALUES (2414, '513226', '金川县', '513200');
INSERT INTO `address_areas` VALUES (2415, '513227', '小金县', '513200');
INSERT INTO `address_areas` VALUES (2416, '513228', '黑水县', '513200');
INSERT INTO `address_areas` VALUES (2417, '513229', '马尔康县', '513200');
INSERT INTO `address_areas` VALUES (2418, '513230', '壤塘县', '513200');
INSERT INTO `address_areas` VALUES (2419, '513231', '阿坝县', '513200');
INSERT INTO `address_areas` VALUES (2420, '513232', '若尔盖县', '513200');
INSERT INTO `address_areas` VALUES (2421, '513233', '红原县', '513200');
INSERT INTO `address_areas` VALUES (2422, '513321', '康定县', '513300');
INSERT INTO `address_areas` VALUES (2423, '513322', '泸定县', '513300');
INSERT INTO `address_areas` VALUES (2424, '513323', '丹巴县', '513300');
INSERT INTO `address_areas` VALUES (2425, '513324', '九龙县', '513300');
INSERT INTO `address_areas` VALUES (2426, '513325', '雅江县', '513300');
INSERT INTO `address_areas` VALUES (2427, '513326', '道孚县', '513300');
INSERT INTO `address_areas` VALUES (2428, '513327', '炉霍县', '513300');
INSERT INTO `address_areas` VALUES (2429, '513328', '甘孜县', '513300');
INSERT INTO `address_areas` VALUES (2430, '513329', '新龙县', '513300');
INSERT INTO `address_areas` VALUES (2431, '513330', '德格县', '513300');
INSERT INTO `address_areas` VALUES (2432, '513331', '白玉县', '513300');
INSERT INTO `address_areas` VALUES (2433, '513332', '石渠县', '513300');
INSERT INTO `address_areas` VALUES (2434, '513333', '色达县', '513300');
INSERT INTO `address_areas` VALUES (2435, '513334', '理塘县', '513300');
INSERT INTO `address_areas` VALUES (2436, '513335', '巴塘县', '513300');
INSERT INTO `address_areas` VALUES (2437, '513336', '乡城县', '513300');
INSERT INTO `address_areas` VALUES (2438, '513337', '稻城县', '513300');
INSERT INTO `address_areas` VALUES (2439, '513338', '得荣县', '513300');
INSERT INTO `address_areas` VALUES (2440, '513401', '西昌市', '513400');
INSERT INTO `address_areas` VALUES (2441, '513422', '木里藏族自治县', '513400');
INSERT INTO `address_areas` VALUES (2442, '513423', '盐源县', '513400');
INSERT INTO `address_areas` VALUES (2443, '513424', '德昌县', '513400');
INSERT INTO `address_areas` VALUES (2444, '513425', '会理县', '513400');
INSERT INTO `address_areas` VALUES (2445, '513426', '会东县', '513400');
INSERT INTO `address_areas` VALUES (2446, '513427', '宁南县', '513400');
INSERT INTO `address_areas` VALUES (2447, '513428', '普格县', '513400');
INSERT INTO `address_areas` VALUES (2448, '513429', '布拖县', '513400');
INSERT INTO `address_areas` VALUES (2449, '513430', '金阳县', '513400');
INSERT INTO `address_areas` VALUES (2450, '513431', '昭觉县', '513400');
INSERT INTO `address_areas` VALUES (2451, '513432', '喜德县', '513400');
INSERT INTO `address_areas` VALUES (2452, '513433', '冕宁县', '513400');
INSERT INTO `address_areas` VALUES (2453, '513434', '越西县', '513400');
INSERT INTO `address_areas` VALUES (2454, '513435', '甘洛县', '513400');
INSERT INTO `address_areas` VALUES (2455, '513436', '美姑县', '513400');
INSERT INTO `address_areas` VALUES (2456, '513437', '雷波县', '513400');
INSERT INTO `address_areas` VALUES (2457, '520101', '市辖区', '520100');
INSERT INTO `address_areas` VALUES (2458, '520102', '南明区', '520100');
INSERT INTO `address_areas` VALUES (2459, '520103', '云岩区', '520100');
INSERT INTO `address_areas` VALUES (2460, '520111', '花溪区', '520100');
INSERT INTO `address_areas` VALUES (2461, '520112', '乌当区', '520100');
INSERT INTO `address_areas` VALUES (2462, '520113', '白云区', '520100');
INSERT INTO `address_areas` VALUES (2463, '520114', '小河区', '520100');
INSERT INTO `address_areas` VALUES (2464, '520121', '开阳县', '520100');
INSERT INTO `address_areas` VALUES (2465, '520122', '息烽县', '520100');
INSERT INTO `address_areas` VALUES (2466, '520123', '修文县', '520100');
INSERT INTO `address_areas` VALUES (2467, '520181', '清镇市', '520100');
INSERT INTO `address_areas` VALUES (2468, '520201', '钟山区', '520200');
INSERT INTO `address_areas` VALUES (2469, '520203', '六枝特区', '520200');
INSERT INTO `address_areas` VALUES (2470, '520221', '水城县', '520200');
INSERT INTO `address_areas` VALUES (2471, '520222', '盘　县', '520200');
INSERT INTO `address_areas` VALUES (2472, '520301', '市辖区', '520300');
INSERT INTO `address_areas` VALUES (2473, '520302', '红花岗区', '520300');
INSERT INTO `address_areas` VALUES (2474, '520303', '汇川区', '520300');
INSERT INTO `address_areas` VALUES (2475, '520321', '遵义县', '520300');
INSERT INTO `address_areas` VALUES (2476, '520322', '桐梓县', '520300');
INSERT INTO `address_areas` VALUES (2477, '520323', '绥阳县', '520300');
INSERT INTO `address_areas` VALUES (2478, '520324', '正安县', '520300');
INSERT INTO `address_areas` VALUES (2479, '520325', '道真仡佬族苗族自治县', '520300');
INSERT INTO `address_areas` VALUES (2480, '520326', '务川仡佬族苗族自治县', '520300');
INSERT INTO `address_areas` VALUES (2481, '520327', '凤冈县', '520300');
INSERT INTO `address_areas` VALUES (2482, '520328', '湄潭县', '520300');
INSERT INTO `address_areas` VALUES (2483, '520329', '余庆县', '520300');
INSERT INTO `address_areas` VALUES (2484, '520330', '习水县', '520300');
INSERT INTO `address_areas` VALUES (2485, '520381', '赤水市', '520300');
INSERT INTO `address_areas` VALUES (2486, '520382', '仁怀市', '520300');
INSERT INTO `address_areas` VALUES (2487, '520401', '市辖区', '520400');
INSERT INTO `address_areas` VALUES (2488, '520402', '西秀区', '520400');
INSERT INTO `address_areas` VALUES (2489, '520421', '平坝县', '520400');
INSERT INTO `address_areas` VALUES (2490, '520422', '普定县', '520400');
INSERT INTO `address_areas` VALUES (2491, '520423', '镇宁布依族苗族自治县', '520400');
INSERT INTO `address_areas` VALUES (2492, '520424', '关岭布依族苗族自治县', '520400');
INSERT INTO `address_areas` VALUES (2493, '520425', '紫云苗族布依族自治县', '520400');
INSERT INTO `address_areas` VALUES (2494, '522201', '铜仁市', '522200');
INSERT INTO `address_areas` VALUES (2495, '522222', '江口县', '522200');
INSERT INTO `address_areas` VALUES (2496, '522223', '玉屏侗族自治县', '522200');
INSERT INTO `address_areas` VALUES (2497, '522224', '石阡县', '522200');
INSERT INTO `address_areas` VALUES (2498, '522225', '思南县', '522200');
INSERT INTO `address_areas` VALUES (2499, '522226', '印江土家族苗族自治县', '522200');
INSERT INTO `address_areas` VALUES (2500, '522227', '德江县', '522200');
INSERT INTO `address_areas` VALUES (2501, '522228', '沿河土家族自治县', '522200');
INSERT INTO `address_areas` VALUES (2502, '522229', '松桃苗族自治县', '522200');
INSERT INTO `address_areas` VALUES (2503, '522230', '万山特区', '522200');
INSERT INTO `address_areas` VALUES (2504, '522301', '兴义市', '522300');
INSERT INTO `address_areas` VALUES (2505, '522322', '兴仁县', '522300');
INSERT INTO `address_areas` VALUES (2506, '522323', '普安县', '522300');
INSERT INTO `address_areas` VALUES (2507, '522324', '晴隆县', '522300');
INSERT INTO `address_areas` VALUES (2508, '522325', '贞丰县', '522300');
INSERT INTO `address_areas` VALUES (2509, '522326', '望谟县', '522300');
INSERT INTO `address_areas` VALUES (2510, '522327', '册亨县', '522300');
INSERT INTO `address_areas` VALUES (2511, '522328', '安龙县', '522300');
INSERT INTO `address_areas` VALUES (2512, '522401', '毕节市', '522400');
INSERT INTO `address_areas` VALUES (2513, '522422', '大方县', '522400');
INSERT INTO `address_areas` VALUES (2514, '522423', '黔西县', '522400');
INSERT INTO `address_areas` VALUES (2515, '522424', '金沙县', '522400');
INSERT INTO `address_areas` VALUES (2516, '522425', '织金县', '522400');
INSERT INTO `address_areas` VALUES (2517, '522426', '纳雍县', '522400');
INSERT INTO `address_areas` VALUES (2518, '522427', '威宁彝族回族苗族自治县', '522400');
INSERT INTO `address_areas` VALUES (2519, '522428', '赫章县', '522400');
INSERT INTO `address_areas` VALUES (2520, '522601', '凯里市', '522600');
INSERT INTO `address_areas` VALUES (2521, '522622', '黄平县', '522600');
INSERT INTO `address_areas` VALUES (2522, '522623', '施秉县', '522600');
INSERT INTO `address_areas` VALUES (2523, '522624', '三穗县', '522600');
INSERT INTO `address_areas` VALUES (2524, '522625', '镇远县', '522600');
INSERT INTO `address_areas` VALUES (2525, '522626', '岑巩县', '522600');
INSERT INTO `address_areas` VALUES (2526, '522627', '天柱县', '522600');
INSERT INTO `address_areas` VALUES (2527, '522628', '锦屏县', '522600');
INSERT INTO `address_areas` VALUES (2528, '522629', '剑河县', '522600');
INSERT INTO `address_areas` VALUES (2529, '522630', '台江县', '522600');
INSERT INTO `address_areas` VALUES (2530, '522631', '黎平县', '522600');
INSERT INTO `address_areas` VALUES (2531, '522632', '榕江县', '522600');
INSERT INTO `address_areas` VALUES (2532, '522633', '从江县', '522600');
INSERT INTO `address_areas` VALUES (2533, '522634', '雷山县', '522600');
INSERT INTO `address_areas` VALUES (2534, '522635', '麻江县', '522600');
INSERT INTO `address_areas` VALUES (2535, '522636', '丹寨县', '522600');
INSERT INTO `address_areas` VALUES (2536, '522701', '都匀市', '522700');
INSERT INTO `address_areas` VALUES (2537, '522702', '福泉市', '522700');
INSERT INTO `address_areas` VALUES (2538, '522722', '荔波县', '522700');
INSERT INTO `address_areas` VALUES (2539, '522723', '贵定县', '522700');
INSERT INTO `address_areas` VALUES (2540, '522725', '瓮安县', '522700');
INSERT INTO `address_areas` VALUES (2541, '522726', '独山县', '522700');
INSERT INTO `address_areas` VALUES (2542, '522727', '平塘县', '522700');
INSERT INTO `address_areas` VALUES (2543, '522728', '罗甸县', '522700');
INSERT INTO `address_areas` VALUES (2544, '522729', '长顺县', '522700');
INSERT INTO `address_areas` VALUES (2545, '522730', '龙里县', '522700');
INSERT INTO `address_areas` VALUES (2546, '522731', '惠水县', '522700');
INSERT INTO `address_areas` VALUES (2547, '522732', '三都水族自治县', '522700');
INSERT INTO `address_areas` VALUES (2548, '530101', '市辖区', '530100');
INSERT INTO `address_areas` VALUES (2549, '530102', '五华区', '530100');
INSERT INTO `address_areas` VALUES (2550, '530103', '盘龙区', '530100');
INSERT INTO `address_areas` VALUES (2551, '530111', '官渡区', '530100');
INSERT INTO `address_areas` VALUES (2552, '530112', '西山区', '530100');
INSERT INTO `address_areas` VALUES (2553, '530113', '东川区', '530100');
INSERT INTO `address_areas` VALUES (2554, '530121', '呈贡县', '530100');
INSERT INTO `address_areas` VALUES (2555, '530122', '晋宁县', '530100');
INSERT INTO `address_areas` VALUES (2556, '530124', '富民县', '530100');
INSERT INTO `address_areas` VALUES (2557, '530125', '宜良县', '530100');
INSERT INTO `address_areas` VALUES (2558, '530126', '石林彝族自治县', '530100');
INSERT INTO `address_areas` VALUES (2559, '530127', '嵩明县', '530100');
INSERT INTO `address_areas` VALUES (2560, '530128', '禄劝彝族苗族自治县', '530100');
INSERT INTO `address_areas` VALUES (2561, '530129', '寻甸回族彝族自治县', '530100');
INSERT INTO `address_areas` VALUES (2562, '530181', '安宁市', '530100');
INSERT INTO `address_areas` VALUES (2563, '530301', '市辖区', '530300');
INSERT INTO `address_areas` VALUES (2564, '530302', '麒麟区', '530300');
INSERT INTO `address_areas` VALUES (2565, '530321', '马龙县', '530300');
INSERT INTO `address_areas` VALUES (2566, '530322', '陆良县', '530300');
INSERT INTO `address_areas` VALUES (2567, '530323', '师宗县', '530300');
INSERT INTO `address_areas` VALUES (2568, '530324', '罗平县', '530300');
INSERT INTO `address_areas` VALUES (2569, '530325', '富源县', '530300');
INSERT INTO `address_areas` VALUES (2570, '530326', '会泽县', '530300');
INSERT INTO `address_areas` VALUES (2571, '530328', '沾益县', '530300');
INSERT INTO `address_areas` VALUES (2572, '530381', '宣威市', '530300');
INSERT INTO `address_areas` VALUES (2573, '530401', '市辖区', '530400');
INSERT INTO `address_areas` VALUES (2574, '530402', '红塔区', '530400');
INSERT INTO `address_areas` VALUES (2575, '530421', '江川县', '530400');
INSERT INTO `address_areas` VALUES (2576, '530422', '澄江县', '530400');
INSERT INTO `address_areas` VALUES (2577, '530423', '通海县', '530400');
INSERT INTO `address_areas` VALUES (2578, '530424', '华宁县', '530400');
INSERT INTO `address_areas` VALUES (2579, '530425', '易门县', '530400');
INSERT INTO `address_areas` VALUES (2580, '530426', '峨山彝族自治县', '530400');
INSERT INTO `address_areas` VALUES (2581, '530427', '新平彝族傣族自治县', '530400');
INSERT INTO `address_areas` VALUES (2582, '530428', '元江哈尼族彝族傣族自治县', '530400');
INSERT INTO `address_areas` VALUES (2583, '530501', '市辖区', '530500');
INSERT INTO `address_areas` VALUES (2584, '530502', '隆阳区', '530500');
INSERT INTO `address_areas` VALUES (2585, '530521', '施甸县', '530500');
INSERT INTO `address_areas` VALUES (2586, '530522', '腾冲县', '530500');
INSERT INTO `address_areas` VALUES (2587, '530523', '龙陵县', '530500');
INSERT INTO `address_areas` VALUES (2588, '530524', '昌宁县', '530500');
INSERT INTO `address_areas` VALUES (2589, '530601', '市辖区', '530600');
INSERT INTO `address_areas` VALUES (2590, '530602', '昭阳区', '530600');
INSERT INTO `address_areas` VALUES (2591, '530621', '鲁甸县', '530600');
INSERT INTO `address_areas` VALUES (2592, '530622', '巧家县', '530600');
INSERT INTO `address_areas` VALUES (2593, '530623', '盐津县', '530600');
INSERT INTO `address_areas` VALUES (2594, '530624', '大关县', '530600');
INSERT INTO `address_areas` VALUES (2595, '530625', '永善县', '530600');
INSERT INTO `address_areas` VALUES (2596, '530626', '绥江县', '530600');
INSERT INTO `address_areas` VALUES (2597, '530627', '镇雄县', '530600');
INSERT INTO `address_areas` VALUES (2598, '530628', '彝良县', '530600');
INSERT INTO `address_areas` VALUES (2599, '530629', '威信县', '530600');
INSERT INTO `address_areas` VALUES (2600, '530630', '水富县', '530600');
INSERT INTO `address_areas` VALUES (2601, '530701', '市辖区', '530700');
INSERT INTO `address_areas` VALUES (2602, '530702', '古城区', '530700');
INSERT INTO `address_areas` VALUES (2603, '530721', '玉龙纳西族自治县', '530700');
INSERT INTO `address_areas` VALUES (2604, '530722', '永胜县', '530700');
INSERT INTO `address_areas` VALUES (2605, '530723', '华坪县', '530700');
INSERT INTO `address_areas` VALUES (2606, '530724', '宁蒗彝族自治县', '530700');
INSERT INTO `address_areas` VALUES (2607, '530801', '市辖区', '530800');
INSERT INTO `address_areas` VALUES (2608, '530802', '翠云区', '530800');
INSERT INTO `address_areas` VALUES (2609, '530821', '普洱哈尼族彝族自治县', '530800');
INSERT INTO `address_areas` VALUES (2610, '530822', '墨江哈尼族自治县', '530800');
INSERT INTO `address_areas` VALUES (2611, '530823', '景东彝族自治县', '530800');
INSERT INTO `address_areas` VALUES (2612, '530824', '景谷傣族彝族自治县', '530800');
INSERT INTO `address_areas` VALUES (2613, '530825', '镇沅彝族哈尼族拉祜族自治县', '530800');
INSERT INTO `address_areas` VALUES (2614, '530826', '江城哈尼族彝族自治县', '530800');
INSERT INTO `address_areas` VALUES (2615, '530827', '孟连傣族拉祜族佤族自治县', '530800');
INSERT INTO `address_areas` VALUES (2616, '530828', '澜沧拉祜族自治县', '530800');
INSERT INTO `address_areas` VALUES (2617, '530829', '西盟佤族自治县', '530800');
INSERT INTO `address_areas` VALUES (2618, '530901', '市辖区', '530900');
INSERT INTO `address_areas` VALUES (2619, '530902', '临翔区', '530900');
INSERT INTO `address_areas` VALUES (2620, '530921', '凤庆县', '530900');
INSERT INTO `address_areas` VALUES (2621, '530922', '云　县', '530900');
INSERT INTO `address_areas` VALUES (2622, '530923', '永德县', '530900');
INSERT INTO `address_areas` VALUES (2623, '530924', '镇康县', '530900');
INSERT INTO `address_areas` VALUES (2624, '530925', '双江拉祜族佤族布朗族傣族自治县', '530900');
INSERT INTO `address_areas` VALUES (2625, '530926', '耿马傣族佤族自治县', '530900');
INSERT INTO `address_areas` VALUES (2626, '530927', '沧源佤族自治县', '530900');
INSERT INTO `address_areas` VALUES (2627, '532301', '楚雄市', '532300');
INSERT INTO `address_areas` VALUES (2628, '532322', '双柏县', '532300');
INSERT INTO `address_areas` VALUES (2629, '532323', '牟定县', '532300');
INSERT INTO `address_areas` VALUES (2630, '532324', '南华县', '532300');
INSERT INTO `address_areas` VALUES (2631, '532325', '姚安县', '532300');
INSERT INTO `address_areas` VALUES (2632, '532326', '大姚县', '532300');
INSERT INTO `address_areas` VALUES (2633, '532327', '永仁县', '532300');
INSERT INTO `address_areas` VALUES (2634, '532328', '元谋县', '532300');
INSERT INTO `address_areas` VALUES (2635, '532329', '武定县', '532300');
INSERT INTO `address_areas` VALUES (2636, '532331', '禄丰县', '532300');
INSERT INTO `address_areas` VALUES (2637, '532501', '个旧市', '532500');
INSERT INTO `address_areas` VALUES (2638, '532502', '开远市', '532500');
INSERT INTO `address_areas` VALUES (2639, '532522', '蒙自县', '532500');
INSERT INTO `address_areas` VALUES (2640, '532523', '屏边苗族自治县', '532500');
INSERT INTO `address_areas` VALUES (2641, '532524', '建水县', '532500');
INSERT INTO `address_areas` VALUES (2642, '532525', '石屏县', '532500');
INSERT INTO `address_areas` VALUES (2643, '532526', '弥勒县', '532500');
INSERT INTO `address_areas` VALUES (2644, '532527', '泸西县', '532500');
INSERT INTO `address_areas` VALUES (2645, '532528', '元阳县', '532500');
INSERT INTO `address_areas` VALUES (2646, '532529', '红河县', '532500');
INSERT INTO `address_areas` VALUES (2647, '532530', '金平苗族瑶族傣族自治县', '532500');
INSERT INTO `address_areas` VALUES (2648, '532531', '绿春县', '532500');
INSERT INTO `address_areas` VALUES (2649, '532532', '河口瑶族自治县', '532500');
INSERT INTO `address_areas` VALUES (2650, '532621', '文山县', '532600');
INSERT INTO `address_areas` VALUES (2651, '532622', '砚山县', '532600');
INSERT INTO `address_areas` VALUES (2652, '532623', '西畴县', '532600');
INSERT INTO `address_areas` VALUES (2653, '532624', '麻栗坡县', '532600');
INSERT INTO `address_areas` VALUES (2654, '532625', '马关县', '532600');
INSERT INTO `address_areas` VALUES (2655, '532626', '丘北县', '532600');
INSERT INTO `address_areas` VALUES (2656, '532627', '广南县', '532600');
INSERT INTO `address_areas` VALUES (2657, '532628', '富宁县', '532600');
INSERT INTO `address_areas` VALUES (2658, '532801', '景洪市', '532800');
INSERT INTO `address_areas` VALUES (2659, '532822', '勐海县', '532800');
INSERT INTO `address_areas` VALUES (2660, '532823', '勐腊县', '532800');
INSERT INTO `address_areas` VALUES (2661, '532901', '大理市', '532900');
INSERT INTO `address_areas` VALUES (2662, '532922', '漾濞彝族自治县', '532900');
INSERT INTO `address_areas` VALUES (2663, '532923', '祥云县', '532900');
INSERT INTO `address_areas` VALUES (2664, '532924', '宾川县', '532900');
INSERT INTO `address_areas` VALUES (2665, '532925', '弥渡县', '532900');
INSERT INTO `address_areas` VALUES (2666, '532926', '南涧彝族自治县', '532900');
INSERT INTO `address_areas` VALUES (2667, '532927', '巍山彝族回族自治县', '532900');
INSERT INTO `address_areas` VALUES (2668, '532928', '永平县', '532900');
INSERT INTO `address_areas` VALUES (2669, '532929', '云龙县', '532900');
INSERT INTO `address_areas` VALUES (2670, '532930', '洱源县', '532900');
INSERT INTO `address_areas` VALUES (2671, '532931', '剑川县', '532900');
INSERT INTO `address_areas` VALUES (2672, '532932', '鹤庆县', '532900');
INSERT INTO `address_areas` VALUES (2673, '533102', '瑞丽市', '533100');
INSERT INTO `address_areas` VALUES (2674, '533103', '潞西市', '533100');
INSERT INTO `address_areas` VALUES (2675, '533122', '梁河县', '533100');
INSERT INTO `address_areas` VALUES (2676, '533123', '盈江县', '533100');
INSERT INTO `address_areas` VALUES (2677, '533124', '陇川县', '533100');
INSERT INTO `address_areas` VALUES (2678, '533321', '泸水县', '533300');
INSERT INTO `address_areas` VALUES (2679, '533323', '福贡县', '533300');
INSERT INTO `address_areas` VALUES (2680, '533324', '贡山独龙族怒族自治县', '533300');
INSERT INTO `address_areas` VALUES (2681, '533325', '兰坪白族普米族自治县', '533300');
INSERT INTO `address_areas` VALUES (2682, '533421', '香格里拉县', '533400');
INSERT INTO `address_areas` VALUES (2683, '533422', '德钦县', '533400');
INSERT INTO `address_areas` VALUES (2684, '533423', '维西傈僳族自治县', '533400');
INSERT INTO `address_areas` VALUES (2685, '540101', '市辖区', '540100');
INSERT INTO `address_areas` VALUES (2686, '540102', '城关区', '540100');
INSERT INTO `address_areas` VALUES (2687, '540121', '林周县', '540100');
INSERT INTO `address_areas` VALUES (2688, '540122', '当雄县', '540100');
INSERT INTO `address_areas` VALUES (2689, '540123', '尼木县', '540100');
INSERT INTO `address_areas` VALUES (2690, '540124', '曲水县', '540100');
INSERT INTO `address_areas` VALUES (2691, '540125', '堆龙德庆县', '540100');
INSERT INTO `address_areas` VALUES (2692, '540126', '达孜县', '540100');
INSERT INTO `address_areas` VALUES (2693, '540127', '墨竹工卡县', '540100');
INSERT INTO `address_areas` VALUES (2694, '542121', '昌都县', '542100');
INSERT INTO `address_areas` VALUES (2695, '542122', '江达县', '542100');
INSERT INTO `address_areas` VALUES (2696, '542123', '贡觉县', '542100');
INSERT INTO `address_areas` VALUES (2697, '542124', '类乌齐县', '542100');
INSERT INTO `address_areas` VALUES (2698, '542125', '丁青县', '542100');
INSERT INTO `address_areas` VALUES (2699, '542126', '察雅县', '542100');
INSERT INTO `address_areas` VALUES (2700, '542127', '八宿县', '542100');
INSERT INTO `address_areas` VALUES (2701, '542128', '左贡县', '542100');
INSERT INTO `address_areas` VALUES (2702, '542129', '芒康县', '542100');
INSERT INTO `address_areas` VALUES (2703, '542132', '洛隆县', '542100');
INSERT INTO `address_areas` VALUES (2704, '542133', '边坝县', '542100');
INSERT INTO `address_areas` VALUES (2705, '542221', '乃东县', '542200');
INSERT INTO `address_areas` VALUES (2706, '542222', '扎囊县', '542200');
INSERT INTO `address_areas` VALUES (2707, '542223', '贡嘎县', '542200');
INSERT INTO `address_areas` VALUES (2708, '542224', '桑日县', '542200');
INSERT INTO `address_areas` VALUES (2709, '542225', '琼结县', '542200');
INSERT INTO `address_areas` VALUES (2710, '542226', '曲松县', '542200');
INSERT INTO `address_areas` VALUES (2711, '542227', '措美县', '542200');
INSERT INTO `address_areas` VALUES (2712, '542228', '洛扎县', '542200');
INSERT INTO `address_areas` VALUES (2713, '542229', '加查县', '542200');
INSERT INTO `address_areas` VALUES (2714, '542231', '隆子县', '542200');
INSERT INTO `address_areas` VALUES (2715, '542232', '错那县', '542200');
INSERT INTO `address_areas` VALUES (2716, '542233', '浪卡子县', '542200');
INSERT INTO `address_areas` VALUES (2717, '542301', '日喀则市', '542300');
INSERT INTO `address_areas` VALUES (2718, '542322', '南木林县', '542300');
INSERT INTO `address_areas` VALUES (2719, '542323', '江孜县', '542300');
INSERT INTO `address_areas` VALUES (2720, '542324', '定日县', '542300');
INSERT INTO `address_areas` VALUES (2721, '542325', '萨迦县', '542300');
INSERT INTO `address_areas` VALUES (2722, '542326', '拉孜县', '542300');
INSERT INTO `address_areas` VALUES (2723, '542327', '昂仁县', '542300');
INSERT INTO `address_areas` VALUES (2724, '542328', '谢通门县', '542300');
INSERT INTO `address_areas` VALUES (2725, '542329', '白朗县', '542300');
INSERT INTO `address_areas` VALUES (2726, '542330', '仁布县', '542300');
INSERT INTO `address_areas` VALUES (2727, '542331', '康马县', '542300');
INSERT INTO `address_areas` VALUES (2728, '542332', '定结县', '542300');
INSERT INTO `address_areas` VALUES (2729, '542333', '仲巴县', '542300');
INSERT INTO `address_areas` VALUES (2730, '542334', '亚东县', '542300');
INSERT INTO `address_areas` VALUES (2731, '542335', '吉隆县', '542300');
INSERT INTO `address_areas` VALUES (2732, '542336', '聂拉木县', '542300');
INSERT INTO `address_areas` VALUES (2733, '542337', '萨嘎县', '542300');
INSERT INTO `address_areas` VALUES (2734, '542338', '岗巴县', '542300');
INSERT INTO `address_areas` VALUES (2735, '542421', '那曲县', '542400');
INSERT INTO `address_areas` VALUES (2736, '542422', '嘉黎县', '542400');
INSERT INTO `address_areas` VALUES (2737, '542423', '比如县', '542400');
INSERT INTO `address_areas` VALUES (2738, '542424', '聂荣县', '542400');
INSERT INTO `address_areas` VALUES (2739, '542425', '安多县', '542400');
INSERT INTO `address_areas` VALUES (2740, '542426', '申扎县', '542400');
INSERT INTO `address_areas` VALUES (2741, '542427', '索　县', '542400');
INSERT INTO `address_areas` VALUES (2742, '542428', '班戈县', '542400');
INSERT INTO `address_areas` VALUES (2743, '542429', '巴青县', '542400');
INSERT INTO `address_areas` VALUES (2744, '542430', '尼玛县', '542400');
INSERT INTO `address_areas` VALUES (2745, '542521', '普兰县', '542500');
INSERT INTO `address_areas` VALUES (2746, '542522', '札达县', '542500');
INSERT INTO `address_areas` VALUES (2747, '542523', '噶尔县', '542500');
INSERT INTO `address_areas` VALUES (2748, '542524', '日土县', '542500');
INSERT INTO `address_areas` VALUES (2749, '542525', '革吉县', '542500');
INSERT INTO `address_areas` VALUES (2750, '542526', '改则县', '542500');
INSERT INTO `address_areas` VALUES (2751, '542527', '措勤县', '542500');
INSERT INTO `address_areas` VALUES (2752, '542621', '林芝县', '542600');
INSERT INTO `address_areas` VALUES (2753, '542622', '工布江达县', '542600');
INSERT INTO `address_areas` VALUES (2754, '542623', '米林县', '542600');
INSERT INTO `address_areas` VALUES (2755, '542624', '墨脱县', '542600');
INSERT INTO `address_areas` VALUES (2756, '542625', '波密县', '542600');
INSERT INTO `address_areas` VALUES (2757, '542626', '察隅县', '542600');
INSERT INTO `address_areas` VALUES (2758, '542627', '朗　县', '542600');
INSERT INTO `address_areas` VALUES (2759, '610101', '市辖区', '610100');
INSERT INTO `address_areas` VALUES (2760, '610102', '新城区', '610100');
INSERT INTO `address_areas` VALUES (2761, '610103', '碑林区', '610100');
INSERT INTO `address_areas` VALUES (2762, '610104', '莲湖区', '610100');
INSERT INTO `address_areas` VALUES (2763, '610111', '灞桥区', '610100');
INSERT INTO `address_areas` VALUES (2764, '610112', '未央区', '610100');
INSERT INTO `address_areas` VALUES (2765, '610113', '雁塔区', '610100');
INSERT INTO `address_areas` VALUES (2766, '610114', '阎良区', '610100');
INSERT INTO `address_areas` VALUES (2767, '610115', '临潼区', '610100');
INSERT INTO `address_areas` VALUES (2768, '610116', '长安区', '610100');
INSERT INTO `address_areas` VALUES (2769, '610122', '蓝田县', '610100');
INSERT INTO `address_areas` VALUES (2770, '610124', '周至县', '610100');
INSERT INTO `address_areas` VALUES (2771, '610125', '户　县', '610100');
INSERT INTO `address_areas` VALUES (2772, '610126', '高陵县', '610100');
INSERT INTO `address_areas` VALUES (2773, '610201', '市辖区', '610200');
INSERT INTO `address_areas` VALUES (2774, '610202', '王益区', '610200');
INSERT INTO `address_areas` VALUES (2775, '610203', '印台区', '610200');
INSERT INTO `address_areas` VALUES (2776, '610204', '耀州区', '610200');
INSERT INTO `address_areas` VALUES (2777, '610222', '宜君县', '610200');
INSERT INTO `address_areas` VALUES (2778, '610301', '市辖区', '610300');
INSERT INTO `address_areas` VALUES (2779, '610302', '渭滨区', '610300');
INSERT INTO `address_areas` VALUES (2780, '610303', '金台区', '610300');
INSERT INTO `address_areas` VALUES (2781, '610304', '陈仓区', '610300');
INSERT INTO `address_areas` VALUES (2782, '610322', '凤翔县', '610300');
INSERT INTO `address_areas` VALUES (2783, '610323', '岐山县', '610300');
INSERT INTO `address_areas` VALUES (2784, '610324', '扶风县', '610300');
INSERT INTO `address_areas` VALUES (2785, '610326', '眉　县', '610300');
INSERT INTO `address_areas` VALUES (2786, '610327', '陇　县', '610300');
INSERT INTO `address_areas` VALUES (2787, '610328', '千阳县', '610300');
INSERT INTO `address_areas` VALUES (2788, '610329', '麟游县', '610300');
INSERT INTO `address_areas` VALUES (2789, '610330', '凤　县', '610300');
INSERT INTO `address_areas` VALUES (2790, '610331', '太白县', '610300');
INSERT INTO `address_areas` VALUES (2791, '610401', '市辖区', '610400');
INSERT INTO `address_areas` VALUES (2792, '610402', '秦都区', '610400');
INSERT INTO `address_areas` VALUES (2793, '610403', '杨凌区', '610400');
INSERT INTO `address_areas` VALUES (2794, '610404', '渭城区', '610400');
INSERT INTO `address_areas` VALUES (2795, '610422', '三原县', '610400');
INSERT INTO `address_areas` VALUES (2796, '610423', '泾阳县', '610400');
INSERT INTO `address_areas` VALUES (2797, '610424', '乾　县', '610400');
INSERT INTO `address_areas` VALUES (2798, '610425', '礼泉县', '610400');
INSERT INTO `address_areas` VALUES (2799, '610426', '永寿县', '610400');
INSERT INTO `address_areas` VALUES (2800, '610427', '彬　县', '610400');
INSERT INTO `address_areas` VALUES (2801, '610428', '长武县', '610400');
INSERT INTO `address_areas` VALUES (2802, '610429', '旬邑县', '610400');
INSERT INTO `address_areas` VALUES (2803, '610430', '淳化县', '610400');
INSERT INTO `address_areas` VALUES (2804, '610431', '武功县', '610400');
INSERT INTO `address_areas` VALUES (2805, '610481', '兴平市', '610400');
INSERT INTO `address_areas` VALUES (2806, '610501', '市辖区', '610500');
INSERT INTO `address_areas` VALUES (2807, '610502', '临渭区', '610500');
INSERT INTO `address_areas` VALUES (2808, '610521', '华　县', '610500');
INSERT INTO `address_areas` VALUES (2809, '610522', '潼关县', '610500');
INSERT INTO `address_areas` VALUES (2810, '610523', '大荔县', '610500');
INSERT INTO `address_areas` VALUES (2811, '610524', '合阳县', '610500');
INSERT INTO `address_areas` VALUES (2812, '610525', '澄城县', '610500');
INSERT INTO `address_areas` VALUES (2813, '610526', '蒲城县', '610500');
INSERT INTO `address_areas` VALUES (2814, '610527', '白水县', '610500');
INSERT INTO `address_areas` VALUES (2815, '610528', '富平县', '610500');
INSERT INTO `address_areas` VALUES (2816, '610581', '韩城市', '610500');
INSERT INTO `address_areas` VALUES (2817, '610582', '华阴市', '610500');
INSERT INTO `address_areas` VALUES (2818, '610601', '市辖区', '610600');
INSERT INTO `address_areas` VALUES (2819, '610602', '宝塔区', '610600');
INSERT INTO `address_areas` VALUES (2820, '610621', '延长县', '610600');
INSERT INTO `address_areas` VALUES (2821, '610622', '延川县', '610600');
INSERT INTO `address_areas` VALUES (2822, '610623', '子长县', '610600');
INSERT INTO `address_areas` VALUES (2823, '610624', '安塞县', '610600');
INSERT INTO `address_areas` VALUES (2824, '610625', '志丹县', '610600');
INSERT INTO `address_areas` VALUES (2825, '610626', '吴旗县', '610600');
INSERT INTO `address_areas` VALUES (2826, '610627', '甘泉县', '610600');
INSERT INTO `address_areas` VALUES (2827, '610628', '富　县', '610600');
INSERT INTO `address_areas` VALUES (2828, '610629', '洛川县', '610600');
INSERT INTO `address_areas` VALUES (2829, '610630', '宜川县', '610600');
INSERT INTO `address_areas` VALUES (2830, '610631', '黄龙县', '610600');
INSERT INTO `address_areas` VALUES (2831, '610632', '黄陵县', '610600');
INSERT INTO `address_areas` VALUES (2832, '610701', '市辖区', '610700');
INSERT INTO `address_areas` VALUES (2833, '610702', '汉台区', '610700');
INSERT INTO `address_areas` VALUES (2834, '610721', '南郑县', '610700');
INSERT INTO `address_areas` VALUES (2835, '610722', '城固县', '610700');
INSERT INTO `address_areas` VALUES (2836, '610723', '洋　县', '610700');
INSERT INTO `address_areas` VALUES (2837, '610724', '西乡县', '610700');
INSERT INTO `address_areas` VALUES (2838, '610725', '勉　县', '610700');
INSERT INTO `address_areas` VALUES (2839, '610726', '宁强县', '610700');
INSERT INTO `address_areas` VALUES (2840, '610727', '略阳县', '610700');
INSERT INTO `address_areas` VALUES (2841, '610728', '镇巴县', '610700');
INSERT INTO `address_areas` VALUES (2842, '610729', '留坝县', '610700');
INSERT INTO `address_areas` VALUES (2843, '610730', '佛坪县', '610700');
INSERT INTO `address_areas` VALUES (2844, '610801', '市辖区', '610800');
INSERT INTO `address_areas` VALUES (2845, '610802', '榆阳区', '610800');
INSERT INTO `address_areas` VALUES (2846, '610821', '神木县', '610800');
INSERT INTO `address_areas` VALUES (2847, '610822', '府谷县', '610800');
INSERT INTO `address_areas` VALUES (2848, '610823', '横山县', '610800');
INSERT INTO `address_areas` VALUES (2849, '610824', '靖边县', '610800');
INSERT INTO `address_areas` VALUES (2850, '610825', '定边县', '610800');
INSERT INTO `address_areas` VALUES (2851, '610826', '绥德县', '610800');
INSERT INTO `address_areas` VALUES (2852, '610827', '米脂县', '610800');
INSERT INTO `address_areas` VALUES (2853, '610828', '佳　县', '610800');
INSERT INTO `address_areas` VALUES (2854, '610829', '吴堡县', '610800');
INSERT INTO `address_areas` VALUES (2855, '610830', '清涧县', '610800');
INSERT INTO `address_areas` VALUES (2856, '610831', '子洲县', '610800');
INSERT INTO `address_areas` VALUES (2857, '610901', '市辖区', '610900');
INSERT INTO `address_areas` VALUES (2858, '610902', '汉滨区', '610900');
INSERT INTO `address_areas` VALUES (2859, '610921', '汉阴县', '610900');
INSERT INTO `address_areas` VALUES (2860, '610922', '石泉县', '610900');
INSERT INTO `address_areas` VALUES (2861, '610923', '宁陕县', '610900');
INSERT INTO `address_areas` VALUES (2862, '610924', '紫阳县', '610900');
INSERT INTO `address_areas` VALUES (2863, '610925', '岚皋县', '610900');
INSERT INTO `address_areas` VALUES (2864, '610926', '平利县', '610900');
INSERT INTO `address_areas` VALUES (2865, '610927', '镇坪县', '610900');
INSERT INTO `address_areas` VALUES (2866, '610928', '旬阳县', '610900');
INSERT INTO `address_areas` VALUES (2867, '610929', '白河县', '610900');
INSERT INTO `address_areas` VALUES (2868, '611001', '市辖区', '611000');
INSERT INTO `address_areas` VALUES (2869, '611002', '商州区', '611000');
INSERT INTO `address_areas` VALUES (2870, '611021', '洛南县', '611000');
INSERT INTO `address_areas` VALUES (2871, '611022', '丹凤县', '611000');
INSERT INTO `address_areas` VALUES (2872, '611023', '商南县', '611000');
INSERT INTO `address_areas` VALUES (2873, '611024', '山阳县', '611000');
INSERT INTO `address_areas` VALUES (2874, '611025', '镇安县', '611000');
INSERT INTO `address_areas` VALUES (2875, '611026', '柞水县', '611000');
INSERT INTO `address_areas` VALUES (2876, '620101', '市辖区', '620100');
INSERT INTO `address_areas` VALUES (2877, '620102', '城关区', '620100');
INSERT INTO `address_areas` VALUES (2878, '620103', '七里河区', '620100');
INSERT INTO `address_areas` VALUES (2879, '620104', '西固区', '620100');
INSERT INTO `address_areas` VALUES (2880, '620105', '安宁区', '620100');
INSERT INTO `address_areas` VALUES (2881, '620111', '红古区', '620100');
INSERT INTO `address_areas` VALUES (2882, '620121', '永登县', '620100');
INSERT INTO `address_areas` VALUES (2883, '620122', '皋兰县', '620100');
INSERT INTO `address_areas` VALUES (2884, '620123', '榆中县', '620100');
INSERT INTO `address_areas` VALUES (2885, '620201', '市辖区', '620200');
INSERT INTO `address_areas` VALUES (2886, '620301', '市辖区', '620300');
INSERT INTO `address_areas` VALUES (2887, '620302', '金川区', '620300');
INSERT INTO `address_areas` VALUES (2888, '620321', '永昌县', '620300');
INSERT INTO `address_areas` VALUES (2889, '620401', '市辖区', '620400');
INSERT INTO `address_areas` VALUES (2890, '620402', '白银区', '620400');
INSERT INTO `address_areas` VALUES (2891, '620403', '平川区', '620400');
INSERT INTO `address_areas` VALUES (2892, '620421', '靖远县', '620400');
INSERT INTO `address_areas` VALUES (2893, '620422', '会宁县', '620400');
INSERT INTO `address_areas` VALUES (2894, '620423', '景泰县', '620400');
INSERT INTO `address_areas` VALUES (2895, '620501', '市辖区', '620500');
INSERT INTO `address_areas` VALUES (2896, '620502', '秦城区', '620500');
INSERT INTO `address_areas` VALUES (2897, '620503', '北道区', '620500');
INSERT INTO `address_areas` VALUES (2898, '620521', '清水县', '620500');
INSERT INTO `address_areas` VALUES (2899, '620522', '秦安县', '620500');
INSERT INTO `address_areas` VALUES (2900, '620523', '甘谷县', '620500');
INSERT INTO `address_areas` VALUES (2901, '620524', '武山县', '620500');
INSERT INTO `address_areas` VALUES (2902, '620525', '张家川回族自治县', '620500');
INSERT INTO `address_areas` VALUES (2903, '620601', '市辖区', '620600');
INSERT INTO `address_areas` VALUES (2904, '620602', '凉州区', '620600');
INSERT INTO `address_areas` VALUES (2905, '620621', '民勤县', '620600');
INSERT INTO `address_areas` VALUES (2906, '620622', '古浪县', '620600');
INSERT INTO `address_areas` VALUES (2907, '620623', '天祝藏族自治县', '620600');
INSERT INTO `address_areas` VALUES (2908, '620701', '市辖区', '620700');
INSERT INTO `address_areas` VALUES (2909, '620702', '甘州区', '620700');
INSERT INTO `address_areas` VALUES (2910, '620721', '肃南裕固族自治县', '620700');
INSERT INTO `address_areas` VALUES (2911, '620722', '民乐县', '620700');
INSERT INTO `address_areas` VALUES (2912, '620723', '临泽县', '620700');
INSERT INTO `address_areas` VALUES (2913, '620724', '高台县', '620700');
INSERT INTO `address_areas` VALUES (2914, '620725', '山丹县', '620700');
INSERT INTO `address_areas` VALUES (2915, '620801', '市辖区', '620800');
INSERT INTO `address_areas` VALUES (2916, '620802', '崆峒区', '620800');
INSERT INTO `address_areas` VALUES (2917, '620821', '泾川县', '620800');
INSERT INTO `address_areas` VALUES (2918, '620822', '灵台县', '620800');
INSERT INTO `address_areas` VALUES (2919, '620823', '崇信县', '620800');
INSERT INTO `address_areas` VALUES (2920, '620824', '华亭县', '620800');
INSERT INTO `address_areas` VALUES (2921, '620825', '庄浪县', '620800');
INSERT INTO `address_areas` VALUES (2922, '620826', '静宁县', '620800');
INSERT INTO `address_areas` VALUES (2923, '620901', '市辖区', '620900');
INSERT INTO `address_areas` VALUES (2924, '620902', '肃州区', '620900');
INSERT INTO `address_areas` VALUES (2925, '620921', '金塔县', '620900');
INSERT INTO `address_areas` VALUES (2926, '620922', '安西县', '620900');
INSERT INTO `address_areas` VALUES (2927, '620923', '肃北蒙古族自治县', '620900');
INSERT INTO `address_areas` VALUES (2928, '620924', '阿克塞哈萨克族自治县', '620900');
INSERT INTO `address_areas` VALUES (2929, '620981', '玉门市', '620900');
INSERT INTO `address_areas` VALUES (2930, '620982', '敦煌市', '620900');
INSERT INTO `address_areas` VALUES (2931, '621001', '市辖区', '621000');
INSERT INTO `address_areas` VALUES (2932, '621002', '西峰区', '621000');
INSERT INTO `address_areas` VALUES (2933, '621021', '庆城县', '621000');
INSERT INTO `address_areas` VALUES (2934, '621022', '环　县', '621000');
INSERT INTO `address_areas` VALUES (2935, '621023', '华池县', '621000');
INSERT INTO `address_areas` VALUES (2936, '621024', '合水县', '621000');
INSERT INTO `address_areas` VALUES (2937, '621025', '正宁县', '621000');
INSERT INTO `address_areas` VALUES (2938, '621026', '宁　县', '621000');
INSERT INTO `address_areas` VALUES (2939, '621027', '镇原县', '621000');
INSERT INTO `address_areas` VALUES (2940, '621101', '市辖区', '621100');
INSERT INTO `address_areas` VALUES (2941, '621102', '安定区', '621100');
INSERT INTO `address_areas` VALUES (2942, '621121', '通渭县', '621100');
INSERT INTO `address_areas` VALUES (2943, '621122', '陇西县', '621100');
INSERT INTO `address_areas` VALUES (2944, '621123', '渭源县', '621100');
INSERT INTO `address_areas` VALUES (2945, '621124', '临洮县', '621100');
INSERT INTO `address_areas` VALUES (2946, '621125', '漳　县', '621100');
INSERT INTO `address_areas` VALUES (2947, '621126', '岷　县', '621100');
INSERT INTO `address_areas` VALUES (2948, '621201', '市辖区', '621200');
INSERT INTO `address_areas` VALUES (2949, '621202', '武都区', '621200');
INSERT INTO `address_areas` VALUES (2950, '621221', '成　县', '621200');
INSERT INTO `address_areas` VALUES (2951, '621222', '文　县', '621200');
INSERT INTO `address_areas` VALUES (2952, '621223', '宕昌县', '621200');
INSERT INTO `address_areas` VALUES (2953, '621224', '康　县', '621200');
INSERT INTO `address_areas` VALUES (2954, '621225', '西和县', '621200');
INSERT INTO `address_areas` VALUES (2955, '621226', '礼　县', '621200');
INSERT INTO `address_areas` VALUES (2956, '621227', '徽　县', '621200');
INSERT INTO `address_areas` VALUES (2957, '621228', '两当县', '621200');
INSERT INTO `address_areas` VALUES (2958, '622901', '临夏市', '622900');
INSERT INTO `address_areas` VALUES (2959, '622921', '临夏县', '622900');
INSERT INTO `address_areas` VALUES (2960, '622922', '康乐县', '622900');
INSERT INTO `address_areas` VALUES (2961, '622923', '永靖县', '622900');
INSERT INTO `address_areas` VALUES (2962, '622924', '广河县', '622900');
INSERT INTO `address_areas` VALUES (2963, '622925', '和政县', '622900');
INSERT INTO `address_areas` VALUES (2964, '622926', '东乡族自治县', '622900');
INSERT INTO `address_areas` VALUES (2965, '622927', '积石山保安族东乡族撒拉族自治县', '622900');
INSERT INTO `address_areas` VALUES (2966, '623001', '合作市', '623000');
INSERT INTO `address_areas` VALUES (2967, '623021', '临潭县', '623000');
INSERT INTO `address_areas` VALUES (2968, '623022', '卓尼县', '623000');
INSERT INTO `address_areas` VALUES (2969, '623023', '舟曲县', '623000');
INSERT INTO `address_areas` VALUES (2970, '623024', '迭部县', '623000');
INSERT INTO `address_areas` VALUES (2971, '623025', '玛曲县', '623000');
INSERT INTO `address_areas` VALUES (2972, '623026', '碌曲县', '623000');
INSERT INTO `address_areas` VALUES (2973, '623027', '夏河县', '623000');
INSERT INTO `address_areas` VALUES (2974, '630101', '市辖区', '630100');
INSERT INTO `address_areas` VALUES (2975, '630102', '城东区', '630100');
INSERT INTO `address_areas` VALUES (2976, '630103', '城中区', '630100');
INSERT INTO `address_areas` VALUES (2977, '630104', '城西区', '630100');
INSERT INTO `address_areas` VALUES (2978, '630105', '城北区', '630100');
INSERT INTO `address_areas` VALUES (2979, '630121', '大通回族土族自治县', '630100');
INSERT INTO `address_areas` VALUES (2980, '630122', '湟中县', '630100');
INSERT INTO `address_areas` VALUES (2981, '630123', '湟源县', '630100');
INSERT INTO `address_areas` VALUES (2982, '632121', '平安县', '632100');
INSERT INTO `address_areas` VALUES (2983, '632122', '民和回族土族自治县', '632100');
INSERT INTO `address_areas` VALUES (2984, '632123', '乐都县', '632100');
INSERT INTO `address_areas` VALUES (2985, '632126', '互助土族自治县', '632100');
INSERT INTO `address_areas` VALUES (2986, '632127', '化隆回族自治县', '632100');
INSERT INTO `address_areas` VALUES (2987, '632128', '循化撒拉族自治县', '632100');
INSERT INTO `address_areas` VALUES (2988, '632221', '门源回族自治县', '632200');
INSERT INTO `address_areas` VALUES (2989, '632222', '祁连县', '632200');
INSERT INTO `address_areas` VALUES (2990, '632223', '海晏县', '632200');
INSERT INTO `address_areas` VALUES (2991, '632224', '刚察县', '632200');
INSERT INTO `address_areas` VALUES (2992, '632321', '同仁县', '632300');
INSERT INTO `address_areas` VALUES (2993, '632322', '尖扎县', '632300');
INSERT INTO `address_areas` VALUES (2994, '632323', '泽库县', '632300');
INSERT INTO `address_areas` VALUES (2995, '632324', '河南蒙古族自治县', '632300');
INSERT INTO `address_areas` VALUES (2996, '632521', '共和县', '632500');
INSERT INTO `address_areas` VALUES (2997, '632522', '同德县', '632500');
INSERT INTO `address_areas` VALUES (2998, '632523', '贵德县', '632500');
INSERT INTO `address_areas` VALUES (2999, '632524', '兴海县', '632500');
INSERT INTO `address_areas` VALUES (3000, '632525', '贵南县', '632500');
INSERT INTO `address_areas` VALUES (3001, '632621', '玛沁县', '632600');
INSERT INTO `address_areas` VALUES (3002, '632622', '班玛县', '632600');
INSERT INTO `address_areas` VALUES (3003, '632623', '甘德县', '632600');
INSERT INTO `address_areas` VALUES (3004, '632624', '达日县', '632600');
INSERT INTO `address_areas` VALUES (3005, '632625', '久治县', '632600');
INSERT INTO `address_areas` VALUES (3006, '632626', '玛多县', '632600');
INSERT INTO `address_areas` VALUES (3007, '632721', '玉树县', '632700');
INSERT INTO `address_areas` VALUES (3008, '632722', '杂多县', '632700');
INSERT INTO `address_areas` VALUES (3009, '632723', '称多县', '632700');
INSERT INTO `address_areas` VALUES (3010, '632724', '治多县', '632700');
INSERT INTO `address_areas` VALUES (3011, '632725', '囊谦县', '632700');
INSERT INTO `address_areas` VALUES (3012, '632726', '曲麻莱县', '632700');
INSERT INTO `address_areas` VALUES (3013, '632801', '格尔木市', '632800');
INSERT INTO `address_areas` VALUES (3014, '632802', '德令哈市', '632800');
INSERT INTO `address_areas` VALUES (3015, '632821', '乌兰县', '632800');
INSERT INTO `address_areas` VALUES (3016, '632822', '都兰县', '632800');
INSERT INTO `address_areas` VALUES (3017, '632823', '天峻县', '632800');
INSERT INTO `address_areas` VALUES (3018, '640101', '市辖区', '640100');
INSERT INTO `address_areas` VALUES (3019, '640104', '兴庆区', '640100');
INSERT INTO `address_areas` VALUES (3020, '640105', '西夏区', '640100');
INSERT INTO `address_areas` VALUES (3021, '640106', '金凤区', '640100');
INSERT INTO `address_areas` VALUES (3022, '640121', '永宁县', '640100');
INSERT INTO `address_areas` VALUES (3023, '640122', '贺兰县', '640100');
INSERT INTO `address_areas` VALUES (3024, '640181', '灵武市', '640100');
INSERT INTO `address_areas` VALUES (3025, '640201', '市辖区', '640200');
INSERT INTO `address_areas` VALUES (3026, '640202', '大武口区', '640200');
INSERT INTO `address_areas` VALUES (3027, '640205', '惠农区', '640200');
INSERT INTO `address_areas` VALUES (3028, '640221', '平罗县', '640200');
INSERT INTO `address_areas` VALUES (3029, '640301', '市辖区', '640300');
INSERT INTO `address_areas` VALUES (3030, '640302', '利通区', '640300');
INSERT INTO `address_areas` VALUES (3031, '640323', '盐池县', '640300');
INSERT INTO `address_areas` VALUES (3032, '640324', '同心县', '640300');
INSERT INTO `address_areas` VALUES (3033, '640381', '青铜峡市', '640300');
INSERT INTO `address_areas` VALUES (3034, '640401', '市辖区', '640400');
INSERT INTO `address_areas` VALUES (3035, '640402', '原州区', '640400');
INSERT INTO `address_areas` VALUES (3036, '640422', '西吉县', '640400');
INSERT INTO `address_areas` VALUES (3037, '640423', '隆德县', '640400');
INSERT INTO `address_areas` VALUES (3038, '640424', '泾源县', '640400');
INSERT INTO `address_areas` VALUES (3039, '640425', '彭阳县', '640400');
INSERT INTO `address_areas` VALUES (3040, '640501', '市辖区', '640500');
INSERT INTO `address_areas` VALUES (3041, '640502', '沙坡头区', '640500');
INSERT INTO `address_areas` VALUES (3042, '640521', '中宁县', '640500');
INSERT INTO `address_areas` VALUES (3043, '640522', '海原县', '640500');
INSERT INTO `address_areas` VALUES (3044, '650101', '市辖区', '650100');
INSERT INTO `address_areas` VALUES (3045, '650102', '天山区', '650100');
INSERT INTO `address_areas` VALUES (3046, '650103', '沙依巴克区', '650100');
INSERT INTO `address_areas` VALUES (3047, '650104', '新市区', '650100');
INSERT INTO `address_areas` VALUES (3048, '650105', '水磨沟区', '650100');
INSERT INTO `address_areas` VALUES (3049, '650106', '头屯河区', '650100');
INSERT INTO `address_areas` VALUES (3050, '650107', '达坂城区', '650100');
INSERT INTO `address_areas` VALUES (3051, '650108', '东山区', '650100');
INSERT INTO `address_areas` VALUES (3052, '650121', '乌鲁木齐县', '650100');
INSERT INTO `address_areas` VALUES (3053, '650201', '市辖区', '650200');
INSERT INTO `address_areas` VALUES (3054, '650202', '独山子区', '650200');
INSERT INTO `address_areas` VALUES (3055, '650203', '克拉玛依区', '650200');
INSERT INTO `address_areas` VALUES (3056, '650204', '白碱滩区', '650200');
INSERT INTO `address_areas` VALUES (3057, '650205', '乌尔禾区', '650200');
INSERT INTO `address_areas` VALUES (3058, '652101', '吐鲁番市', '652100');
INSERT INTO `address_areas` VALUES (3059, '652122', '鄯善县', '652100');
INSERT INTO `address_areas` VALUES (3060, '652123', '托克逊县', '652100');
INSERT INTO `address_areas` VALUES (3061, '652201', '哈密市', '652200');
INSERT INTO `address_areas` VALUES (3062, '652222', '巴里坤哈萨克自治县', '652200');
INSERT INTO `address_areas` VALUES (3063, '652223', '伊吾县', '652200');
INSERT INTO `address_areas` VALUES (3064, '652301', '昌吉市', '652300');
INSERT INTO `address_areas` VALUES (3065, '652302', '阜康市', '652300');
INSERT INTO `address_areas` VALUES (3066, '652303', '米泉市', '652300');
INSERT INTO `address_areas` VALUES (3067, '652323', '呼图壁县', '652300');
INSERT INTO `address_areas` VALUES (3068, '652324', '玛纳斯县', '652300');
INSERT INTO `address_areas` VALUES (3069, '652325', '奇台县', '652300');
INSERT INTO `address_areas` VALUES (3070, '652327', '吉木萨尔县', '652300');
INSERT INTO `address_areas` VALUES (3071, '652328', '木垒哈萨克自治县', '652300');
INSERT INTO `address_areas` VALUES (3072, '652701', '博乐市', '652700');
INSERT INTO `address_areas` VALUES (3073, '652722', '精河县', '652700');
INSERT INTO `address_areas` VALUES (3074, '652723', '温泉县', '652700');
INSERT INTO `address_areas` VALUES (3075, '652801', '库尔勒市', '652800');
INSERT INTO `address_areas` VALUES (3076, '652822', '轮台县', '652800');
INSERT INTO `address_areas` VALUES (3077, '652823', '尉犁县', '652800');
INSERT INTO `address_areas` VALUES (3078, '652824', '若羌县', '652800');
INSERT INTO `address_areas` VALUES (3079, '652825', '且末县', '652800');
INSERT INTO `address_areas` VALUES (3080, '652826', '焉耆回族自治县', '652800');
INSERT INTO `address_areas` VALUES (3081, '652827', '和静县', '652800');
INSERT INTO `address_areas` VALUES (3082, '652828', '和硕县', '652800');
INSERT INTO `address_areas` VALUES (3083, '652829', '博湖县', '652800');
INSERT INTO `address_areas` VALUES (3084, '652901', '阿克苏市', '652900');
INSERT INTO `address_areas` VALUES (3085, '652922', '温宿县', '652900');
INSERT INTO `address_areas` VALUES (3086, '652923', '库车县', '652900');
INSERT INTO `address_areas` VALUES (3087, '652924', '沙雅县', '652900');
INSERT INTO `address_areas` VALUES (3088, '652925', '新和县', '652900');
INSERT INTO `address_areas` VALUES (3089, '652926', '拜城县', '652900');
INSERT INTO `address_areas` VALUES (3090, '652927', '乌什县', '652900');
INSERT INTO `address_areas` VALUES (3091, '652928', '阿瓦提县', '652900');
INSERT INTO `address_areas` VALUES (3092, '652929', '柯坪县', '652900');
INSERT INTO `address_areas` VALUES (3093, '653001', '阿图什市', '653000');
INSERT INTO `address_areas` VALUES (3094, '653022', '阿克陶县', '653000');
INSERT INTO `address_areas` VALUES (3095, '653023', '阿合奇县', '653000');
INSERT INTO `address_areas` VALUES (3096, '653024', '乌恰县', '653000');
INSERT INTO `address_areas` VALUES (3097, '653101', '喀什市', '653100');
INSERT INTO `address_areas` VALUES (3098, '653121', '疏附县', '653100');
INSERT INTO `address_areas` VALUES (3099, '653122', '疏勒县', '653100');
INSERT INTO `address_areas` VALUES (3100, '653123', '英吉沙县', '653100');
INSERT INTO `address_areas` VALUES (3101, '653124', '泽普县', '653100');
INSERT INTO `address_areas` VALUES (3102, '653125', '莎车县', '653100');
INSERT INTO `address_areas` VALUES (3103, '653126', '叶城县', '653100');
INSERT INTO `address_areas` VALUES (3104, '653127', '麦盖提县', '653100');
INSERT INTO `address_areas` VALUES (3105, '653128', '岳普湖县', '653100');
INSERT INTO `address_areas` VALUES (3106, '653129', '伽师县', '653100');
INSERT INTO `address_areas` VALUES (3107, '653130', '巴楚县', '653100');
INSERT INTO `address_areas` VALUES (3108, '653131', '塔什库尔干塔吉克自治县', '653100');
INSERT INTO `address_areas` VALUES (3109, '653201', '和田市', '653200');
INSERT INTO `address_areas` VALUES (3110, '653221', '和田县', '653200');
INSERT INTO `address_areas` VALUES (3111, '653222', '墨玉县', '653200');
INSERT INTO `address_areas` VALUES (3112, '653223', '皮山县', '653200');
INSERT INTO `address_areas` VALUES (3113, '653224', '洛浦县', '653200');
INSERT INTO `address_areas` VALUES (3114, '653225', '策勒县', '653200');
INSERT INTO `address_areas` VALUES (3115, '653226', '于田县', '653200');
INSERT INTO `address_areas` VALUES (3116, '653227', '民丰县', '653200');
INSERT INTO `address_areas` VALUES (3117, '654002', '伊宁市', '654000');
INSERT INTO `address_areas` VALUES (3118, '654003', '奎屯市', '654000');
INSERT INTO `address_areas` VALUES (3119, '654021', '伊宁县', '654000');
INSERT INTO `address_areas` VALUES (3120, '654022', '察布查尔锡伯自治县', '654000');
INSERT INTO `address_areas` VALUES (3121, '654023', '霍城县', '654000');
INSERT INTO `address_areas` VALUES (3122, '654024', '巩留县', '654000');
INSERT INTO `address_areas` VALUES (3123, '654025', '新源县', '654000');
INSERT INTO `address_areas` VALUES (3124, '654026', '昭苏县', '654000');
INSERT INTO `address_areas` VALUES (3125, '654027', '特克斯县', '654000');
INSERT INTO `address_areas` VALUES (3126, '654028', '尼勒克县', '654000');
INSERT INTO `address_areas` VALUES (3127, '654201', '塔城市', '654200');
INSERT INTO `address_areas` VALUES (3128, '654202', '乌苏市', '654200');
INSERT INTO `address_areas` VALUES (3129, '654221', '额敏县', '654200');
INSERT INTO `address_areas` VALUES (3130, '654223', '沙湾县', '654200');
INSERT INTO `address_areas` VALUES (3131, '654224', '托里县', '654200');
INSERT INTO `address_areas` VALUES (3132, '654225', '裕民县', '654200');
INSERT INTO `address_areas` VALUES (3133, '654226', '和布克赛尔蒙古自治县', '654200');
INSERT INTO `address_areas` VALUES (3134, '654301', '阿勒泰市', '654300');
INSERT INTO `address_areas` VALUES (3135, '654321', '布尔津县', '654300');
INSERT INTO `address_areas` VALUES (3136, '654322', '富蕴县', '654300');
INSERT INTO `address_areas` VALUES (3137, '654323', '福海县', '654300');
INSERT INTO `address_areas` VALUES (3138, '654324', '哈巴河县', '654300');
INSERT INTO `address_areas` VALUES (3139, '654325', '青河县', '654300');
INSERT INTO `address_areas` VALUES (3140, '654326', '吉木乃县', '654300');
INSERT INTO `address_areas` VALUES (3141, '659001', '石河子市', '659000');
INSERT INTO `address_areas` VALUES (3142, '659002', '阿拉尔市', '659000');
INSERT INTO `address_areas` VALUES (3143, '659003', '图木舒克市', '659000');
INSERT INTO `address_areas` VALUES (3144, '659004', '五家渠市', '659000');

-- ----------------------------
-- Table structure for address_cities
-- ----------------------------
DROP TABLE IF EXISTS `address_cities`;
CREATE TABLE `address_cities`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city_id` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市ID',
  `city` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市名称',
  `province_id` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 346 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '市表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_cities
-- ----------------------------
INSERT INTO `address_cities` VALUES (1, '110100', '北京市', '110000');
INSERT INTO `address_cities` VALUES (2, '1102xx', '北京下属县', '1100xx');
INSERT INTO `address_cities` VALUES (3, '120100', '天津市', '120000');
INSERT INTO `address_cities` VALUES (4, '1202xx', '天津下属县', '1200xx');
INSERT INTO `address_cities` VALUES (5, '130100', '石家庄市', '130000');
INSERT INTO `address_cities` VALUES (6, '130200', '唐山市', '130000');
INSERT INTO `address_cities` VALUES (7, '130300', '秦皇岛市', '130000');
INSERT INTO `address_cities` VALUES (8, '130400', '邯郸市', '130000');
INSERT INTO `address_cities` VALUES (9, '130500', '邢台市', '130000');
INSERT INTO `address_cities` VALUES (10, '130600', '保定市', '130000');
INSERT INTO `address_cities` VALUES (11, '130700', '张家口市', '130000');
INSERT INTO `address_cities` VALUES (12, '130800', '承德市', '130000');
INSERT INTO `address_cities` VALUES (13, '130900', '沧州市', '130000');
INSERT INTO `address_cities` VALUES (14, '131000', '廊坊市', '130000');
INSERT INTO `address_cities` VALUES (15, '131100', '衡水市', '130000');
INSERT INTO `address_cities` VALUES (16, '140100', '太原市', '140000');
INSERT INTO `address_cities` VALUES (17, '140200', '大同市', '140000');
INSERT INTO `address_cities` VALUES (18, '140300', '阳泉市', '140000');
INSERT INTO `address_cities` VALUES (19, '140400', '长治市', '140000');
INSERT INTO `address_cities` VALUES (20, '140500', '晋城市', '140000');
INSERT INTO `address_cities` VALUES (21, '140600', '朔州市', '140000');
INSERT INTO `address_cities` VALUES (22, '140700', '晋中市', '140000');
INSERT INTO `address_cities` VALUES (23, '140800', '运城市', '140000');
INSERT INTO `address_cities` VALUES (24, '140900', '忻州市', '140000');
INSERT INTO `address_cities` VALUES (25, '141000', '临汾市', '140000');
INSERT INTO `address_cities` VALUES (26, '141100', '吕梁市', '140000');
INSERT INTO `address_cities` VALUES (27, '150100', '呼和浩特市', '150000');
INSERT INTO `address_cities` VALUES (28, '150200', '包头市', '150000');
INSERT INTO `address_cities` VALUES (29, '150300', '乌海市', '150000');
INSERT INTO `address_cities` VALUES (30, '150400', '赤峰市', '150000');
INSERT INTO `address_cities` VALUES (31, '150500', '通辽市', '150000');
INSERT INTO `address_cities` VALUES (32, '150600', '鄂尔多斯市', '150000');
INSERT INTO `address_cities` VALUES (33, '150700', '呼伦贝尔市', '150000');
INSERT INTO `address_cities` VALUES (34, '150800', '巴彦淖尔市', '150000');
INSERT INTO `address_cities` VALUES (35, '150900', '乌兰察布市', '150000');
INSERT INTO `address_cities` VALUES (36, '152200', '兴安盟', '150000');
INSERT INTO `address_cities` VALUES (37, '152500', '锡林郭勒盟', '150000');
INSERT INTO `address_cities` VALUES (38, '152900', '阿拉善盟', '150000');
INSERT INTO `address_cities` VALUES (39, '210100', '沈阳市', '210000');
INSERT INTO `address_cities` VALUES (40, '210200', '大连市', '210000');
INSERT INTO `address_cities` VALUES (41, '210300', '鞍山市', '210000');
INSERT INTO `address_cities` VALUES (42, '210400', '抚顺市', '210000');
INSERT INTO `address_cities` VALUES (43, '210500', '本溪市', '210000');
INSERT INTO `address_cities` VALUES (44, '210600', '丹东市', '210000');
INSERT INTO `address_cities` VALUES (45, '210700', '锦州市', '210000');
INSERT INTO `address_cities` VALUES (46, '210800', '营口市', '210000');
INSERT INTO `address_cities` VALUES (47, '210900', '阜新市', '210000');
INSERT INTO `address_cities` VALUES (48, '211000', '辽阳市', '210000');
INSERT INTO `address_cities` VALUES (49, '211100', '盘锦市', '210000');
INSERT INTO `address_cities` VALUES (50, '211200', '铁岭市', '210000');
INSERT INTO `address_cities` VALUES (51, '211300', '朝阳市', '210000');
INSERT INTO `address_cities` VALUES (52, '211400', '葫芦岛市', '210000');
INSERT INTO `address_cities` VALUES (53, '220100', '长春市', '220000');
INSERT INTO `address_cities` VALUES (54, '220200', '吉林市', '220000');
INSERT INTO `address_cities` VALUES (55, '220300', '四平市', '220000');
INSERT INTO `address_cities` VALUES (56, '220400', '辽源市', '220000');
INSERT INTO `address_cities` VALUES (57, '220500', '通化市', '220000');
INSERT INTO `address_cities` VALUES (58, '220600', '白山市', '220000');
INSERT INTO `address_cities` VALUES (59, '220700', '松原市', '220000');
INSERT INTO `address_cities` VALUES (60, '220800', '白城市', '220000');
INSERT INTO `address_cities` VALUES (61, '222400', '延边朝鲜族自治州', '220000');
INSERT INTO `address_cities` VALUES (62, '230100', '哈尔滨市', '230000');
INSERT INTO `address_cities` VALUES (63, '230200', '齐齐哈尔市', '230000');
INSERT INTO `address_cities` VALUES (64, '230300', '鸡西市', '230000');
INSERT INTO `address_cities` VALUES (65, '230400', '鹤岗市', '230000');
INSERT INTO `address_cities` VALUES (66, '230500', '双鸭山市', '230000');
INSERT INTO `address_cities` VALUES (67, '230600', '大庆市', '230000');
INSERT INTO `address_cities` VALUES (68, '230700', '伊春市', '230000');
INSERT INTO `address_cities` VALUES (69, '230800', '佳木斯市', '230000');
INSERT INTO `address_cities` VALUES (70, '230900', '七台河市', '230000');
INSERT INTO `address_cities` VALUES (71, '231000', '牡丹江市', '230000');
INSERT INTO `address_cities` VALUES (72, '231100', '黑河市', '230000');
INSERT INTO `address_cities` VALUES (73, '231200', '绥化市', '230000');
INSERT INTO `address_cities` VALUES (74, '232700', '大兴安岭地区', '230000');
INSERT INTO `address_cities` VALUES (75, '310100', '上海市', '310000');
INSERT INTO `address_cities` VALUES (76, '3102xx', '上海下属县', '3100xx');
INSERT INTO `address_cities` VALUES (77, '320100', '南京市', '320000');
INSERT INTO `address_cities` VALUES (78, '320200', '无锡市', '320000');
INSERT INTO `address_cities` VALUES (79, '320300', '徐州市', '320000');
INSERT INTO `address_cities` VALUES (80, '320400', '常州市', '320000');
INSERT INTO `address_cities` VALUES (81, '320500', '苏州市', '320000');
INSERT INTO `address_cities` VALUES (82, '320600', '南通市', '320000');
INSERT INTO `address_cities` VALUES (83, '320700', '连云港市', '320000');
INSERT INTO `address_cities` VALUES (84, '320800', '淮安市', '320000');
INSERT INTO `address_cities` VALUES (85, '320900', '盐城市', '320000');
INSERT INTO `address_cities` VALUES (86, '321000', '扬州市', '320000');
INSERT INTO `address_cities` VALUES (87, '321100', '镇江市', '320000');
INSERT INTO `address_cities` VALUES (88, '321200', '泰州市', '320000');
INSERT INTO `address_cities` VALUES (89, '321300', '宿迁市', '320000');
INSERT INTO `address_cities` VALUES (90, '330100', '杭州市', '330000');
INSERT INTO `address_cities` VALUES (91, '330200', '宁波市', '330000');
INSERT INTO `address_cities` VALUES (92, '330300', '温州市', '330000');
INSERT INTO `address_cities` VALUES (93, '330400', '嘉兴市', '330000');
INSERT INTO `address_cities` VALUES (94, '330500', '湖州市', '330000');
INSERT INTO `address_cities` VALUES (95, '330600', '绍兴市', '330000');
INSERT INTO `address_cities` VALUES (96, '330700', '金华市', '330000');
INSERT INTO `address_cities` VALUES (97, '330800', '衢州市', '330000');
INSERT INTO `address_cities` VALUES (98, '330900', '舟山市', '330000');
INSERT INTO `address_cities` VALUES (99, '331000', '台州市', '330000');
INSERT INTO `address_cities` VALUES (100, '331100', '丽水市', '330000');
INSERT INTO `address_cities` VALUES (101, '340100', '合肥市', '340000');
INSERT INTO `address_cities` VALUES (102, '340200', '芜湖市', '340000');
INSERT INTO `address_cities` VALUES (103, '340300', '蚌埠市', '340000');
INSERT INTO `address_cities` VALUES (104, '340400', '淮南市', '340000');
INSERT INTO `address_cities` VALUES (105, '340500', '马鞍山市', '340000');
INSERT INTO `address_cities` VALUES (106, '340600', '淮北市', '340000');
INSERT INTO `address_cities` VALUES (107, '340700', '铜陵市', '340000');
INSERT INTO `address_cities` VALUES (108, '340800', '安庆市', '340000');
INSERT INTO `address_cities` VALUES (109, '341000', '黄山市', '340000');
INSERT INTO `address_cities` VALUES (110, '341100', '滁州市', '340000');
INSERT INTO `address_cities` VALUES (111, '341200', '阜阳市', '340000');
INSERT INTO `address_cities` VALUES (112, '341300', '宿州市', '340000');
INSERT INTO `address_cities` VALUES (113, '341400', '巢湖市', '340000');
INSERT INTO `address_cities` VALUES (114, '341500', '六安市', '340000');
INSERT INTO `address_cities` VALUES (115, '341600', '亳州市', '340000');
INSERT INTO `address_cities` VALUES (116, '341700', '池州市', '340000');
INSERT INTO `address_cities` VALUES (117, '341800', '宣城市', '340000');
INSERT INTO `address_cities` VALUES (118, '350100', '福州市', '350000');
INSERT INTO `address_cities` VALUES (119, '350200', '厦门市', '350000');
INSERT INTO `address_cities` VALUES (120, '350300', '莆田市', '350000');
INSERT INTO `address_cities` VALUES (121, '350400', '三明市', '350000');
INSERT INTO `address_cities` VALUES (122, '350500', '泉州市', '350000');
INSERT INTO `address_cities` VALUES (123, '350600', '漳州市', '350000');
INSERT INTO `address_cities` VALUES (124, '350700', '南平市', '350000');
INSERT INTO `address_cities` VALUES (125, '350800', '龙岩市', '350000');
INSERT INTO `address_cities` VALUES (126, '350900', '宁德市', '350000');
INSERT INTO `address_cities` VALUES (127, '360100', '南昌市', '360000');
INSERT INTO `address_cities` VALUES (128, '360200', '景德镇市', '360000');
INSERT INTO `address_cities` VALUES (129, '360300', '萍乡市', '360000');
INSERT INTO `address_cities` VALUES (130, '360400', '九江市', '360000');
INSERT INTO `address_cities` VALUES (131, '360500', '新余市', '360000');
INSERT INTO `address_cities` VALUES (132, '360600', '鹰潭市', '360000');
INSERT INTO `address_cities` VALUES (133, '360700', '赣州市', '360000');
INSERT INTO `address_cities` VALUES (134, '360800', '吉安市', '360000');
INSERT INTO `address_cities` VALUES (135, '360900', '宜春市', '360000');
INSERT INTO `address_cities` VALUES (136, '361000', '抚州市', '360000');
INSERT INTO `address_cities` VALUES (137, '361100', '上饶市', '360000');
INSERT INTO `address_cities` VALUES (138, '370100', '济南市', '370000');
INSERT INTO `address_cities` VALUES (139, '370200', '青岛市', '370000');
INSERT INTO `address_cities` VALUES (140, '370300', '淄博市', '370000');
INSERT INTO `address_cities` VALUES (141, '370400', '枣庄市', '370000');
INSERT INTO `address_cities` VALUES (142, '370500', '东营市', '370000');
INSERT INTO `address_cities` VALUES (143, '370600', '烟台市', '370000');
INSERT INTO `address_cities` VALUES (144, '370700', '潍坊市', '370000');
INSERT INTO `address_cities` VALUES (145, '370800', '济宁市', '370000');
INSERT INTO `address_cities` VALUES (146, '370900', '泰安市', '370000');
INSERT INTO `address_cities` VALUES (147, '371000', '威海市', '370000');
INSERT INTO `address_cities` VALUES (148, '371100', '日照市', '370000');
INSERT INTO `address_cities` VALUES (149, '371200', '莱芜市', '370000');
INSERT INTO `address_cities` VALUES (150, '371300', '临沂市', '370000');
INSERT INTO `address_cities` VALUES (151, '371400', '德州市', '370000');
INSERT INTO `address_cities` VALUES (152, '371500', '聊城市', '370000');
INSERT INTO `address_cities` VALUES (153, '371600', '滨州市', '370000');
INSERT INTO `address_cities` VALUES (154, '371700', '荷泽市', '370000');
INSERT INTO `address_cities` VALUES (155, '410100', '郑州市', '410000');
INSERT INTO `address_cities` VALUES (156, '410200', '开封市', '410000');
INSERT INTO `address_cities` VALUES (157, '410300', '洛阳市', '410000');
INSERT INTO `address_cities` VALUES (158, '410400', '平顶山市', '410000');
INSERT INTO `address_cities` VALUES (159, '410500', '安阳市', '410000');
INSERT INTO `address_cities` VALUES (160, '410600', '鹤壁市', '410000');
INSERT INTO `address_cities` VALUES (161, '410700', '新乡市', '410000');
INSERT INTO `address_cities` VALUES (162, '410800', '焦作市', '410000');
INSERT INTO `address_cities` VALUES (163, '410900', '濮阳市', '410000');
INSERT INTO `address_cities` VALUES (164, '411000', '许昌市', '410000');
INSERT INTO `address_cities` VALUES (165, '411100', '漯河市', '410000');
INSERT INTO `address_cities` VALUES (166, '411200', '三门峡市', '410000');
INSERT INTO `address_cities` VALUES (167, '411300', '南阳市', '410000');
INSERT INTO `address_cities` VALUES (168, '411400', '商丘市', '410000');
INSERT INTO `address_cities` VALUES (169, '411500', '信阳市', '410000');
INSERT INTO `address_cities` VALUES (170, '411600', '周口市', '410000');
INSERT INTO `address_cities` VALUES (171, '411700', '驻马店市', '410000');
INSERT INTO `address_cities` VALUES (172, '420100', '武汉市', '420000');
INSERT INTO `address_cities` VALUES (173, '420200', '黄石市', '420000');
INSERT INTO `address_cities` VALUES (174, '420300', '十堰市', '420000');
INSERT INTO `address_cities` VALUES (175, '420500', '宜昌市', '420000');
INSERT INTO `address_cities` VALUES (176, '420600', '襄樊市', '420000');
INSERT INTO `address_cities` VALUES (177, '420700', '鄂州市', '420000');
INSERT INTO `address_cities` VALUES (178, '420800', '荆门市', '420000');
INSERT INTO `address_cities` VALUES (179, '420900', '孝感市', '420000');
INSERT INTO `address_cities` VALUES (180, '421000', '荆州市', '420000');
INSERT INTO `address_cities` VALUES (181, '421100', '黄冈市', '420000');
INSERT INTO `address_cities` VALUES (182, '421200', '咸宁市', '420000');
INSERT INTO `address_cities` VALUES (183, '421300', '随州市', '420000');
INSERT INTO `address_cities` VALUES (184, '422800', '恩施土家族苗族自治州', '420000');
INSERT INTO `address_cities` VALUES (185, '429000', '省直辖行政单位', '420000');
INSERT INTO `address_cities` VALUES (186, '430100', '长沙市', '430000');
INSERT INTO `address_cities` VALUES (187, '430200', '株洲市', '430000');
INSERT INTO `address_cities` VALUES (188, '430300', '湘潭市', '430000');
INSERT INTO `address_cities` VALUES (189, '430400', '衡阳市', '430000');
INSERT INTO `address_cities` VALUES (190, '430500', '邵阳市', '430000');
INSERT INTO `address_cities` VALUES (191, '430600', '岳阳市', '430000');
INSERT INTO `address_cities` VALUES (192, '430700', '常德市', '430000');
INSERT INTO `address_cities` VALUES (193, '430800', '张家界市', '430000');
INSERT INTO `address_cities` VALUES (194, '430900', '益阳市', '430000');
INSERT INTO `address_cities` VALUES (195, '431000', '郴州市', '430000');
INSERT INTO `address_cities` VALUES (196, '431100', '永州市', '430000');
INSERT INTO `address_cities` VALUES (197, '431200', '怀化市', '430000');
INSERT INTO `address_cities` VALUES (198, '431300', '娄底市', '430000');
INSERT INTO `address_cities` VALUES (199, '433100', '湘西土家族苗族自治州', '430000');
INSERT INTO `address_cities` VALUES (200, '440100', '广州市', '440000');
INSERT INTO `address_cities` VALUES (201, '440200', '韶关市', '440000');
INSERT INTO `address_cities` VALUES (202, '440300', '深圳市', '440000');
INSERT INTO `address_cities` VALUES (203, '440400', '珠海市', '440000');
INSERT INTO `address_cities` VALUES (204, '440500', '汕头市', '440000');
INSERT INTO `address_cities` VALUES (205, '440600', '佛山市', '440000');
INSERT INTO `address_cities` VALUES (206, '440700', '江门市', '440000');
INSERT INTO `address_cities` VALUES (207, '440800', '湛江市', '440000');
INSERT INTO `address_cities` VALUES (208, '440900', '茂名市', '440000');
INSERT INTO `address_cities` VALUES (209, '441200', '肇庆市', '440000');
INSERT INTO `address_cities` VALUES (210, '441300', '惠州市', '440000');
INSERT INTO `address_cities` VALUES (211, '441400', '梅州市', '440000');
INSERT INTO `address_cities` VALUES (212, '441500', '汕尾市', '440000');
INSERT INTO `address_cities` VALUES (213, '441600', '河源市', '440000');
INSERT INTO `address_cities` VALUES (214, '441700', '阳江市', '440000');
INSERT INTO `address_cities` VALUES (215, '441800', '清远市', '440000');
INSERT INTO `address_cities` VALUES (216, '441900', '东莞市', '440000');
INSERT INTO `address_cities` VALUES (217, '442000', '中山市', '440000');
INSERT INTO `address_cities` VALUES (218, '445100', '潮州市', '440000');
INSERT INTO `address_cities` VALUES (219, '445200', '揭阳市', '440000');
INSERT INTO `address_cities` VALUES (220, '445300', '云浮市', '440000');
INSERT INTO `address_cities` VALUES (221, '450100', '南宁市', '450000');
INSERT INTO `address_cities` VALUES (222, '450200', '柳州市', '450000');
INSERT INTO `address_cities` VALUES (223, '450300', '桂林市', '450000');
INSERT INTO `address_cities` VALUES (224, '450400', '梧州市', '450000');
INSERT INTO `address_cities` VALUES (225, '450500', '北海市', '450000');
INSERT INTO `address_cities` VALUES (226, '450600', '防城港市', '450000');
INSERT INTO `address_cities` VALUES (227, '450700', '钦州市', '450000');
INSERT INTO `address_cities` VALUES (228, '450800', '贵港市', '450000');
INSERT INTO `address_cities` VALUES (229, '450900', '玉林市', '450000');
INSERT INTO `address_cities` VALUES (230, '451000', '百色市', '450000');
INSERT INTO `address_cities` VALUES (231, '451100', '贺州市', '450000');
INSERT INTO `address_cities` VALUES (232, '451200', '河池市', '450000');
INSERT INTO `address_cities` VALUES (233, '451300', '来宾市', '450000');
INSERT INTO `address_cities` VALUES (234, '451400', '崇左市', '450000');
INSERT INTO `address_cities` VALUES (235, '460100', '海口市', '460000');
INSERT INTO `address_cities` VALUES (236, '460200', '三亚市', '460000');
INSERT INTO `address_cities` VALUES (237, '469000', '省直辖县级行政单位', '460000');
INSERT INTO `address_cities` VALUES (238, '500100', '重庆市', '500000');
INSERT INTO `address_cities` VALUES (239, '5002xx', '重庆下属县', '5000xx');
INSERT INTO `address_cities` VALUES (240, '5003xx', '重庆下属市', '5000xx');
INSERT INTO `address_cities` VALUES (241, '510100', '成都市', '510000');
INSERT INTO `address_cities` VALUES (242, '510300', '自贡市', '510000');
INSERT INTO `address_cities` VALUES (243, '510400', '攀枝花市', '510000');
INSERT INTO `address_cities` VALUES (244, '510500', '泸州市', '510000');
INSERT INTO `address_cities` VALUES (245, '510600', '德阳市', '510000');
INSERT INTO `address_cities` VALUES (246, '510700', '绵阳市', '510000');
INSERT INTO `address_cities` VALUES (247, '510800', '广元市', '510000');
INSERT INTO `address_cities` VALUES (248, '510900', '遂宁市', '510000');
INSERT INTO `address_cities` VALUES (249, '511000', '内江市', '510000');
INSERT INTO `address_cities` VALUES (250, '511100', '乐山市', '510000');
INSERT INTO `address_cities` VALUES (251, '511300', '南充市', '510000');
INSERT INTO `address_cities` VALUES (252, '511400', '眉山市', '510000');
INSERT INTO `address_cities` VALUES (253, '511500', '宜宾市', '510000');
INSERT INTO `address_cities` VALUES (254, '511600', '广安市', '510000');
INSERT INTO `address_cities` VALUES (255, '511700', '达州市', '510000');
INSERT INTO `address_cities` VALUES (256, '511800', '雅安市', '510000');
INSERT INTO `address_cities` VALUES (257, '511900', '巴中市', '510000');
INSERT INTO `address_cities` VALUES (258, '512000', '资阳市', '510000');
INSERT INTO `address_cities` VALUES (259, '513200', '阿坝藏族羌族自治州', '510000');
INSERT INTO `address_cities` VALUES (260, '513300', '甘孜藏族自治州', '510000');
INSERT INTO `address_cities` VALUES (261, '513400', '凉山彝族自治州', '510000');
INSERT INTO `address_cities` VALUES (262, '520100', '贵阳市', '520000');
INSERT INTO `address_cities` VALUES (263, '520200', '六盘水市', '520000');
INSERT INTO `address_cities` VALUES (264, '520300', '遵义市', '520000');
INSERT INTO `address_cities` VALUES (265, '520400', '安顺市', '520000');
INSERT INTO `address_cities` VALUES (266, '522200', '铜仁地区', '520000');
INSERT INTO `address_cities` VALUES (267, '522300', '黔西南布依族苗族自治州', '520000');
INSERT INTO `address_cities` VALUES (268, '522400', '毕节地区', '520000');
INSERT INTO `address_cities` VALUES (269, '522600', '黔东南苗族侗族自治州', '520000');
INSERT INTO `address_cities` VALUES (270, '522700', '黔南布依族苗族自治州', '520000');
INSERT INTO `address_cities` VALUES (271, '530100', '昆明市', '530000');
INSERT INTO `address_cities` VALUES (272, '530300', '曲靖市', '530000');
INSERT INTO `address_cities` VALUES (273, '530400', '玉溪市', '530000');
INSERT INTO `address_cities` VALUES (274, '530500', '保山市', '530000');
INSERT INTO `address_cities` VALUES (275, '530600', '昭通市', '530000');
INSERT INTO `address_cities` VALUES (276, '530700', '丽江市', '530000');
INSERT INTO `address_cities` VALUES (277, '530800', '思茅市', '530000');
INSERT INTO `address_cities` VALUES (278, '530900', '临沧市', '530000');
INSERT INTO `address_cities` VALUES (279, '532300', '楚雄彝族自治州', '530000');
INSERT INTO `address_cities` VALUES (280, '532500', '红河哈尼族彝族自治州', '530000');
INSERT INTO `address_cities` VALUES (281, '532600', '文山壮族苗族自治州', '530000');
INSERT INTO `address_cities` VALUES (282, '532800', '西双版纳傣族自治州', '530000');
INSERT INTO `address_cities` VALUES (283, '532900', '大理白族自治州', '530000');
INSERT INTO `address_cities` VALUES (284, '533100', '德宏傣族景颇族自治州', '530000');
INSERT INTO `address_cities` VALUES (285, '533300', '怒江傈僳族自治州', '530000');
INSERT INTO `address_cities` VALUES (286, '533400', '迪庆藏族自治州', '530000');
INSERT INTO `address_cities` VALUES (287, '540100', '拉萨市', '540000');
INSERT INTO `address_cities` VALUES (288, '542100', '昌都地区', '540000');
INSERT INTO `address_cities` VALUES (289, '542200', '山南地区', '540000');
INSERT INTO `address_cities` VALUES (290, '542300', '日喀则地区', '540000');
INSERT INTO `address_cities` VALUES (291, '542400', '那曲地区', '540000');
INSERT INTO `address_cities` VALUES (292, '542500', '阿里地区', '540000');
INSERT INTO `address_cities` VALUES (293, '542600', '林芝地区', '540000');
INSERT INTO `address_cities` VALUES (294, '610100', '西安市', '610000');
INSERT INTO `address_cities` VALUES (295, '610200', '铜川市', '610000');
INSERT INTO `address_cities` VALUES (296, '610300', '宝鸡市', '610000');
INSERT INTO `address_cities` VALUES (297, '610400', '咸阳市', '610000');
INSERT INTO `address_cities` VALUES (298, '610500', '渭南市', '610000');
INSERT INTO `address_cities` VALUES (299, '610600', '延安市', '610000');
INSERT INTO `address_cities` VALUES (300, '610700', '汉中市', '610000');
INSERT INTO `address_cities` VALUES (301, '610800', '榆林市', '610000');
INSERT INTO `address_cities` VALUES (302, '610900', '安康市', '610000');
INSERT INTO `address_cities` VALUES (303, '611000', '商洛市', '610000');
INSERT INTO `address_cities` VALUES (304, '620100', '兰州市', '620000');
INSERT INTO `address_cities` VALUES (305, '620200', '嘉峪关市', '620000');
INSERT INTO `address_cities` VALUES (306, '620300', '金昌市', '620000');
INSERT INTO `address_cities` VALUES (307, '620400', '白银市', '620000');
INSERT INTO `address_cities` VALUES (308, '620500', '天水市', '620000');
INSERT INTO `address_cities` VALUES (309, '620600', '武威市', '620000');
INSERT INTO `address_cities` VALUES (310, '620700', '张掖市', '620000');
INSERT INTO `address_cities` VALUES (311, '620800', '平凉市', '620000');
INSERT INTO `address_cities` VALUES (312, '620900', '酒泉市', '620000');
INSERT INTO `address_cities` VALUES (313, '621000', '庆阳市', '620000');
INSERT INTO `address_cities` VALUES (314, '621100', '定西市', '620000');
INSERT INTO `address_cities` VALUES (315, '621200', '陇南市', '620000');
INSERT INTO `address_cities` VALUES (316, '622900', '临夏回族自治州', '620000');
INSERT INTO `address_cities` VALUES (317, '623000', '甘南藏族自治州', '620000');
INSERT INTO `address_cities` VALUES (318, '630100', '西宁市', '630000');
INSERT INTO `address_cities` VALUES (319, '632100', '海东地区', '630000');
INSERT INTO `address_cities` VALUES (320, '632200', '海北藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (321, '632300', '黄南藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (322, '632500', '海南藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (323, '632600', '果洛藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (324, '632700', '玉树藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (325, '632800', '海西蒙古族藏族自治州', '630000');
INSERT INTO `address_cities` VALUES (326, '640100', '银川市', '640000');
INSERT INTO `address_cities` VALUES (327, '640200', '石嘴山市', '640000');
INSERT INTO `address_cities` VALUES (328, '640300', '吴忠市', '640000');
INSERT INTO `address_cities` VALUES (329, '640400', '固原市', '640000');
INSERT INTO `address_cities` VALUES (330, '640500', '中卫市', '640000');
INSERT INTO `address_cities` VALUES (331, '650100', '乌鲁木齐市', '650000');
INSERT INTO `address_cities` VALUES (332, '650200', '克拉玛依市', '650000');
INSERT INTO `address_cities` VALUES (333, '652100', '吐鲁番地区', '650000');
INSERT INTO `address_cities` VALUES (334, '652200', '哈密地区', '650000');
INSERT INTO `address_cities` VALUES (335, '652300', '昌吉回族自治州', '650000');
INSERT INTO `address_cities` VALUES (336, '652700', '博尔塔拉蒙古自治州', '650000');
INSERT INTO `address_cities` VALUES (337, '652800', '巴音郭楞蒙古自治州', '650000');
INSERT INTO `address_cities` VALUES (338, '652900', '阿克苏地区', '650000');
INSERT INTO `address_cities` VALUES (339, '653000', '克孜勒苏柯尔克孜自治州', '650000');
INSERT INTO `address_cities` VALUES (340, '653100', '喀什地区', '650000');
INSERT INTO `address_cities` VALUES (341, '653200', '和田地区', '650000');
INSERT INTO `address_cities` VALUES (342, '654000', '伊犁哈萨克自治州', '650000');
INSERT INTO `address_cities` VALUES (343, '654200', '塔城地区', '650000');
INSERT INTO `address_cities` VALUES (344, '654300', '阿勒泰地区', '650000');
INSERT INTO `address_cities` VALUES (345, '659000', '省直辖行政单位', '650000');

-- ----------------------------
-- Table structure for address_provinces
-- ----------------------------
DROP TABLE IF EXISTS `address_provinces`;
CREATE TABLE `address_provinces`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省ID',
  `province` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省名称',
  `province_easy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省简称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '省表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_provinces
-- ----------------------------
INSERT INTO `address_provinces` VALUES (1, '110000', '北京市', '京');
INSERT INTO `address_provinces` VALUES (2, '120000', '天津市', '津');
INSERT INTO `address_provinces` VALUES (3, '130000', '河北省', '冀');
INSERT INTO `address_provinces` VALUES (4, '140000', '山西省', '晋');
INSERT INTO `address_provinces` VALUES (5, '150000', '内蒙古自治区', '蒙');
INSERT INTO `address_provinces` VALUES (6, '210000', '辽宁省', '辽');
INSERT INTO `address_provinces` VALUES (7, '220000', '吉林省', '吉');
INSERT INTO `address_provinces` VALUES (8, '230000', '黑龙江省', '黑');
INSERT INTO `address_provinces` VALUES (9, '310000', '上海市', '沪');
INSERT INTO `address_provinces` VALUES (10, '320000', '江苏省', '苏');
INSERT INTO `address_provinces` VALUES (11, '330000', '浙江省', '浙');
INSERT INTO `address_provinces` VALUES (12, '340000', '安徽省', '皖');
INSERT INTO `address_provinces` VALUES (13, '350000', '福建省', '闽');
INSERT INTO `address_provinces` VALUES (14, '360000', '江西省', '赣');
INSERT INTO `address_provinces` VALUES (15, '370000', '山东省', '鲁');
INSERT INTO `address_provinces` VALUES (16, '410000', '河南省', '豫');
INSERT INTO `address_provinces` VALUES (17, '420000', '湖北省', '鄂');
INSERT INTO `address_provinces` VALUES (18, '430000', '湖南省', '湘');
INSERT INTO `address_provinces` VALUES (19, '440000', '广东省', '粤');
INSERT INTO `address_provinces` VALUES (20, '450000', '广西壮族自治区', '桂');
INSERT INTO `address_provinces` VALUES (21, '460000', '海南省', '琼');
INSERT INTO `address_provinces` VALUES (22, '500000', '重庆市', '渝');
INSERT INTO `address_provinces` VALUES (23, '510000', '四川省', '川');
INSERT INTO `address_provinces` VALUES (24, '520000', '贵州省', '贵');
INSERT INTO `address_provinces` VALUES (25, '530000', '云南省', '云');
INSERT INTO `address_provinces` VALUES (26, '540000', '西藏自治区', '藏');
INSERT INTO `address_provinces` VALUES (27, '610000', '陕西省', '陕');
INSERT INTO `address_provinces` VALUES (28, '620000', '甘肃省', '甘');
INSERT INTO `address_provinces` VALUES (29, '630000', '青海省', '青');
INSERT INTO `address_provinces` VALUES (30, '640000', '宁夏回族自治区', '宁');
INSERT INTO `address_provinces` VALUES (31, '650000', '新疆维吾尔自治区', '新');
INSERT INTO `address_provinces` VALUES (32, '710000', '台湾省', '台');
INSERT INTO `address_provinces` VALUES (33, '810000', '香港特别行政区', '港');
INSERT INTO `address_provinces` VALUES (34, '820000', '澳门特别行政区', '澳');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '密码',
  `key_sm3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码摘要',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (16, 'admin', 'http://localhost:9998/files/avatar/1737123218141-4C4B22587F3474CA794FEC0CEB5F7D3F.jpg', '202501162325247237', 'BNOB1HdgdSjM6k5x97V4UVa82agnJRnCIfcNMOA4+gapv9XgFt20O7Uta26ZowZNaMopimDpd5spvIZxelYyAkEdX5LeeQpVQu3Z9aKqdk4iXlqwDOLSoli6rFFwKcDHTxT8zyXR', 'FE4B7F2D44A67217DB50EC3A3E7EE597A7E07F4D6056A51E05FA55870396ECD3', '626692428@qq.com', 'admin', '男', '19958500349');

-- ----------------------------
-- Table structure for cainstitution
-- ----------------------------
DROP TABLE IF EXISTS `cainstitution`;
CREATE TABLE `cainstitution`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '密码',
  `key_sm3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码摘要',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'AC机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cainstitution
-- ----------------------------
INSERT INTO `cainstitution` VALUES (1, 'caInstitution', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.QoRCEN8clpncbfUjX7WJaQAAAA?w=178&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202501162359474204', 'BCA2gadgo9NWclXUEk86O8JQ8eO01dNWuKfoufDD/K9+mMWq1WpYQxHLuQWaDeVBtuiZQrsDNMgOP6vS273azWrJkMwNTlRFEbVSiDv48NM3AfLsC6mV23n3qvonnjeOksxUpc2urg==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '1356046169@qq.com', '机构成员A', '男', '19958500349');
INSERT INTO `cainstitution` VALUES (3, 'caInstitution', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.QoRCEN8clpncbfUjX7WJaQAAAA?w=178&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202501170007434994', 'BN0jy4bxOTz6r24IFyFuDiV4ask01bq3WrEvZUQLYOW34D6SJExxwrgra/3oTm+n3jrypbTmSMbDyBSNSKYi3U2IQeVQM1/66Ii655aHa546XcRlr3e3C7cYQjOhk4VFILnUlW/ioQ==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '2563622159@qq.com', '机构成员B', '女', '19958500349');

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代码id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `title` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '问题',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'c/c++',
  `codeMessage` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '代码',
  `runMessage` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '运行信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '代码编写' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES (1, 6, '2025-04-05 19:44:20', '编写hello代码', 'c++', '#include<iostream>\r\nusing namespace std;\r\nint main(){\r\n    cout<<\"hello world\";\r\n    return 0;\r\n}', '开始编译\nD:/application/MinGW/bin/g++.exe F:\\ExamSystem\\SystemCode\\springCloud/files/code\\d006d57d418140d8b6ca3847da755fad.cpp -o F:\\ExamSystem\\SystemCode\\springCloud/files/code\\d006d57d418140d8b6ca3847da755fad.exe\nCompilation successful!\n______________result begin______________\nhello world\n执行完成，退出代码：0\n______________result end______________\n');
INSERT INTO `code` VALUES (2, 6, '2025-04-13 16:28:51', '题目：一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？\n\n程序分析：\n\n假设该数为 x。\n\n1、则：x + 100 = n2, x + 100 + 168 = m2\n\n2、计算等式：m2 - n2 = (m + n)(m - n) = 168\n\n3、设置： m + n = i，m - n = j，i * j =168，i 和 j 至少一个是偶数\n\n4、可得： m = (i + j) / 2， n = (i - j) / 2，i 和 j 要么都是偶数，要么都是奇数。\n\n5、从 3 和 4 推导可知道，i 与 j 均是大于等于 2 的偶数。\n\n6、由于 i * j = 168， j>=2，则 1 < i < 168 / 2 + 1。\n\n7、接下来将 i 的所有数字循环计算即可。', 'c', '#include <stdio.h>\r\n \r\nint main (void)\r\n{\r\n    int  i, j, m, n, x;\r\n    for (i = 1; i < 168 / 2 + 1; i++)\r\n    {\r\n        if (168 % i == 0)\r\n        {\r\n            j = 168 / i;\r\n            if ( i > j && (i + j) % 2 == 0 && (i - j) % 2 == 0)\r\n            {\r\n                m = (i + j) / 2;\r\n                n = (i - j) / 2;\r\n                x = n * n - 100;\r\n                printf (\"%d + 100 = %d * %d\\n\", x, n, n);\r\n                printf (\"%d + 268 = %d * %d\\n\", x, m, m);\r\n            }\r\n        }\r\n    }\r\n    return 0;\r\n}', '开始编译\nD:/application/MinGW/bin/gcc.exe F:\\ExamSystem\\SystemCode\\springCloud/files/code\\84ea1f3eca574ebea96bdc860d5ab4a1.c -o F:\\ExamSystem\\SystemCode\\springCloud/files/code\\84ea1f3eca574ebea96bdc860d5ab4a1.exe\nCompilation successful!\n______________result begin______________\n-99 + 100 = 1 * 1\n-99 + 268 = 13 * 13\n21 + 100 = 11 * 11\n21 + 268 = 17 * 17\n261 + 100 = 19 * 19\n261 + 268 = 23 * 23\n1581 + 100 = 41 * 41\n1581 + 268 = 43 * 43\n执行完成，退出代码：0\n______________result end______________\n');
INSERT INTO `code` VALUES (3, 6, '2025-04-15 21:35:00', '完成字符串比较函数,要求s1大于s2返回1,s1等于s2返回0,s1小于s2返回-1', 'c', '#include<stdio.h>\r\nint StringCompare(const char* s1, const char* s2) {\r\n    while (*s1 && *s1 == *s2) {\r\n        s1++;\r\n        s2++;\r\n    }\r\n    return (*s1 > *s2) ? 1 : (*s1 < *s2) ? -1 : 0;\r\n}\r\nint main(){\r\n    int s1=StringCompare(\"abc\",\"abc\");\r\n    printf(\"%s %s %d\\n\",\"abc\",\"abc\",s1);\r\n    int s2=StringCompare(\"abc\",\"cba\");\r\n    printf(\"%s %s %d\\n\",\"abc\",\"cba\",s2);\r\n    int s3=StringCompare(\"def\",\"abc\");\r\n    printf(\"%s %s %d\\n\",\"def\",\"abc\",s3);\r\n}', '开始编译\nD:/application/MinGW/bin/gcc.exe F:\\ExamSystem\\SystemCode\\springCloud/files/code\\2ccef95cbaac40d68811c7fe7f7ceb5a.c -o F:\\ExamSystem\\SystemCode\\springCloud/files/code\\2ccef95cbaac40d68811c7fe7f7ceb5a.exe\nCompilation successful!\n______________result begin______________\nabc abc 0\nabc cba -1\ndef abc 1\n执行完成，退出代码：0\n______________result end______________\n');
INSERT INTO `code` VALUES (4, 6, '2025-04-15 21:46:04', '完成整数转字符串函数,要求得到传入的整数i的字符串形式(比如123对应\"123\")并放入指针s所存储的地址空间中', 'c', '#include <stdio.h>\r\nvoid IntToString(int i, char* s) {\r\n    long long n = i;\r\n    int sign = 0;\r\n    if (n < 0) {//判断正负\r\n        sign = 1;\r\n        n = -n;\r\n    }\r\n    char temp[32];\r\n    int index = 0;\r\n    if (n == 0) {\r\n        temp[index++] = \'0\';\r\n    } else {//将数字转为字符并进行存储，逆序\r\n        while (n != 0) {\r\n            temp[index++] = (n % 10) + \'0\';\r\n            n /= 10;\r\n        }\r\n    }\r\n    int s_index = 0;\r\n    if (sign) {//如果是负数，补全\r\n        s[s_index++] = \'-\';\r\n    }//进行翻转\r\n    for (int j = index - 1; j >= 0; j--) {\r\n        s[s_index++] = temp[j];\r\n    }\r\n    s[s_index] = \'\\0\';\r\n}\r\nint main() {\r\n    char buffer[32];\r\n    IntToString(0, buffer);\r\n    printf(\"%s\\n\", buffer);\r\n    IntToString(123, buffer);\r\n    printf(\"%s\\n\", buffer);\r\n    IntToString(-456, buffer);\r\n    printf(\"%s\\n\", buffer);\r\n    return 0;\r\n}', '开始编译\nD:/application/MinGW/bin/gcc.exe F:\\ExamSystem\\SystemCode\\springCloud/files/code\\8ca63780de274fa698b1759a89f8b3a0.c -o F:\\ExamSystem\\SystemCode\\springCloud/files/code\\8ca63780de274fa698b1759a89f8b3a0.exe\nCompilation successful!\n______________result begin______________\n0\n123\n-456\n执行完成，退出代码：0\n______________result end______________\n');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_Id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程',
  `begin_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '结束时间',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程编码',
  `message` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '课程介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 5, '数据结构算法分析', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743090608287-R-C.jfif', '0eaYfXD1', '<ul><li><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：数据结构与算法分析</li><li><strong>课程代码</strong>：不同院校有不同设定，例如 CS203</li><li><strong>课程学分</strong>：通常为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息与计算科学等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、实验教学、在线学习、小组讨论相结合的方式。课堂讲授系统讲解理论知识；实验教学让学生进行实践操作；在线学习提供丰富的学习资源；小组讨论培养学生合作交流能力。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>深入理解各种数据结构的定义、特点和存储表示，如线性表、栈、队列、树、图等。</li><li>掌握不同数据结构上的基本操作，如插入、删除、查找、遍历等。</li><li>熟悉常见算法的设计策略和分析方法，如分治法、动态规划、贪心算法等。</li><li>了解算法复杂度分析的概念和方法，能够分析算法的时间复杂度和空间复杂度。</li></ul><h4>能力目标</h4><ul><li>具备运用数据结构和算法知识解决实际问题的能力，能够针对具体问题选择合适的数据结构和算法进行设计和实现。</li><li>提高编程能力，能够使用至少一种编程语言（如 Python、Java 等）实现各种数据结构和算法。</li><li>培养算法设计和分析能力，能够设计高效的算法并对其性能进行评估和优化。</li><li>提升逻辑思维和抽象思维能力，通过对数据结构和算法的学习，增强对问题的抽象和建模能力。</li></ul><h4>素质目标</h4><ul><li>培养严谨的科学态度和创新精神，在算法设计和实现过程中注重细节和正确性，鼓励创新思维。</li><li>增强团队协作和沟通能力，通过小组讨论和项目实践，提高学生的合作能力和交流能力。</li><li>树立正确的知识产权意识，尊重他人的劳动成果，在学习和实践中遵守相关法律法规。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>绪论（2 - 4 学时）</strong><ul><li>数据结构的基本概念，包括数据、数据元素、数据对象、数据结构等。</li><li>算法的定义、特性和描述方法，如自然语言、流程图、伪代码等。</li><li>算法复杂度分析，时间复杂度和空间复杂度的概念、计算方法和渐进表示法。</li></ul></li><li><strong>线性表（6 - 8 学时）</strong><ul><li>线性表的定义和基本操作，如初始化、插入、删除、查找等。</li><li>线性表的顺序存储结构和链式存储结构，包括单链表、循环链表、双向链表等。</li><li>线性表的应用，如多项式的表示和运算、约瑟夫环问题等。</li></ul></li><li><strong>栈和队列（6 - 8 学时）</strong><ul><li>栈的定义、特点和基本操作，如进栈、出栈、判空等。</li><li>栈的顺序存储结构和链式存储结构，栈在表达式求值、递归调用等方面的应用。</li><li>队列的定义、特点和基本操作，如入队、出队、判空等。</li><li>队列的顺序存储结构和链式存储结构，循环队列的实现和应用。</li></ul></li><li><strong>串（4 - 6 学时）</strong><ul><li>串的定义、基本操作和存储结构，如顺序存储、链式存储等。</li><li>串的模式匹配算法，如朴素模式匹配算法、KMP 算法等。</li></ul></li><li><strong>数组和广义表（4 - 6 学时）</strong><ul><li>数组的定义、存储结构和基本操作，多维数组的存储方式。</li><li>特殊矩阵的压缩存储，如对称矩阵、三角矩阵、稀疏矩阵等。</li><li>广义表的定义、存储结构和基本操作，广义表的递归特性和应用。</li></ul></li><li><strong>树和二叉树（8 - 10 学时）</strong><ul><li>树的基本概念、术语和存储结构，如双亲表示法、孩子表示法、孩子兄弟表示法等。</li><li>二叉树的定义、性质和存储结构，如顺序存储、链式存储等。</li><li>二叉树的遍历算法，如前序遍历、中序遍历、后序遍历、层序遍历等。</li><li>线索二叉树的概念和实现，哈夫曼树的构造和应用。</li><li>树和森林与二叉树的转换，树的遍历算法。</li></ul></li><li><strong>图（8 - 10 学时）</strong><ul><li>图的基本概念、术语和存储结构，如邻接矩阵、邻接表等。</li><li>图的遍历算法，如深度优先搜索（DFS）和广度优先搜索（BFS）。</li><li>图的连通性问题，如最小生成树算法（Prim 算法、Kruskal 算法）。</li><li>最短路径问题，如 Dijkstra 算法、Floyd 算法。</li><li>拓扑排序和关键路径算法。</li></ul></li><li><strong>查找（6 - 8 学时）</strong><ul><li>查找的基本概念和性能指标，如平均查找长度（ASL）。</li><li>静态查找表，如顺序查找、折半查找、分块查找等。</li><li>动态查找表，如二叉排序树、平衡二叉树（AVL 树）等。</li><li>哈希表的概念、构造方法和处理冲突的方法，如开放定址法、链地址法等。</li></ul></li><li><strong>排序（8 - 10 学时）</strong><ul><li>排序的基本概念和分类，如内部排序和外部排序。</li><li>插入排序，如直接插入排序、希尔排序等。</li><li>交换排序，如冒泡排序、快速排序等。</li><li>选择排序，如简单选择排序、堆排序等。</li><li>归并排序和基数排序。</li><li>各种排序算法的性能比较和应用场景。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>线性表实验（4 - 6 学时）</strong><ul><li>实现顺序表和单链表的基本操作，如插入、删除、查找等。</li><li>解决线性表的应用问题，如多项式的相加。</li></ul></li><li><strong>栈和队列实验（4 - 6 学时）</strong><ul><li>实现栈和队列的顺序存储结构和链式存储结构。</li><li>利用栈和队列解决实际问题，如表达式求值、迷宫求解。</li></ul></li><li><strong>树和二叉树实验（4 - 6 学时）</strong><ul><li>实现二叉树的链式存储结构和遍历算法。</li><li>构造哈夫曼树并进行编码和解码。</li></ul></li><li><strong>图实验（4 - 6 学时）</strong><ul><li>实现图的邻接矩阵和邻接表存储结构。</li><li>实现图的遍历算法和最小生成树算法。</li></ul></li><li><strong>查找和排序实验（4 - 6 学时）</strong><ul><li>实现各种查找算法和排序算法。</li><li>比较不同查找算法和排序算法的性能。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>涵盖课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试，题型有选择题、填空题、简答题、算法设计题、算法分析题等，全面考查学生对知识的掌握和运用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《数据结构（C 语言版）》，严蔚敏、吴伟民编著，清华大学出版社。</li><li>《数据结构与算法分析 ——C 语言描述（第 2 版）》，Mark Allen Weiss 著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《算法导论（原书第 3 版）》，Thomas H. Cormen 等著，机械工业出版社。</li><li>《Python 数据结构与算法分析（第 2 版）》，Brad Miller、David Ranum 著，人民邮电出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 C、Python 或 Java），能够进行基本的程序设计和调试，理解变量、数据类型、控制结构、函数等概念。</li><li><strong>离散数学</strong>：了解集合、逻辑、关系、图论等离散数学知识，为数据结构和算法的学习提供数学基础。</li></ul></li></ul>');
INSERT INTO `course` VALUES (4, 4, '人工智能基础', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743087715832-pexels-photo-3861969.jpeg', '3P6Ztbzb', '<h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：人工智能基础</li><li><strong>课程代码</strong>：AI001</li><li><strong>课程学分</strong>：3 学分</li><li><strong>课程学时</strong>：48 学时（理论教学 32 学时，实践教学 16 学时）</li><li><strong>授课对象</strong>：计算机科学、电子工程、数学等相关专业本科生；对人工智能感兴趣的非专业学生也可选修。</li><li><strong>授课方式</strong>：线上线下混合式教学，线上通过在线学习平台提供教学视频、资料和作业，线下进行课堂讲授、小组讨论和实践操作指导。</li></ul><h3>课程目标</h3><ul><li><strong>知识目标</strong><ul><li>使学生掌握人工智能的基本概念、发展历程和主要研究领域。</li><li>理解人工智能中的重要算法和模型，如搜索算法、机器学习算法、神经网络等。</li><li>了解人工智能在不同领域的应用现状和发展趋势。</li></ul></li><li><strong>能力目标</strong><ul><li>培养学生运用人工智能方法和技术解决实际问题的能力。</li><li>提高学生的编程能力，能够使用 Python 等编程语言实现简单的人工智能算法。</li><li>提升学生的数据分析和处理能力，能够对数据进行清洗、特征提取和模型评估。</li></ul></li><li><strong>素质目标</strong><ul><li>培养学生的创新思维和团队合作精神，鼓励学生在人工智能领域进行探索和创新。</li><li>增强学生的社会责任感，使学生了解人工智能技术可能带来的伦理和社会问题。</li></ul></li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>人工智能概述（4 学时）</strong><ul><li>人工智能的定义、发展历程和主要研究领域。</li><li>人工智能的应用领域，如医疗、交通、金融等。</li><li>人工智能的发展趋势和挑战。</li></ul></li><li><strong>搜索算法（6 学时）</strong><ul><li>盲目搜索算法，如广度优先搜索、深度优先搜索。</li><li>启发式搜索算法，如 A* 算法、贪婪最佳优先搜索。</li><li>博弈搜索算法，如极小极大算法、Alpha - Beta 剪枝算法。</li></ul></li><li><strong>机器学习基础（8 学时）</strong><ul><li>机器学习的基本概念、模型和算法。</li><li>监督学习算法，如线性回归、逻辑回归、决策树、支持向量机。</li><li>无监督学习算法，如聚类算法、降维算法。</li><li>模型评估和选择，如交叉验证、性能度量。</li></ul></li><li><strong>神经网络与深度学习（8 学时）</strong><ul><li>神经网络的基本结构和原理，如神经元、激活函数、感知机。</li><li>多层感知机和反向传播算法。</li><li>卷积神经网络（CNN）及其在图像识别中的应用。</li><li>循环神经网络（RNN）及其在自然语言处理中的应用。</li></ul></li><li><strong>自然语言处理（4 学时）</strong><ul><li>自然语言处理的基本概念和任务。</li><li>词法分析、句法分析和语义分析。</li><li>文本分类、情感分析和机器翻译。</li></ul></li><li><strong>人工智能伦理与社会（2 学时）</strong><ul><li>人工智能的伦理问题，如隐私保护、算法偏见、就业影响。</li><li>人工智能的社会影响和法律监管。</li><li>人工智能的发展对人类社会的挑战和机遇。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>Python 编程基础（4 学时）</strong><ul><li>Python 语言的基本语法和数据结构。</li><li>Python 编程环境的搭建和使用。</li><li>使用 Python 进行数据处理和可视化。</li></ul></li><li><strong>搜索算法实践（4 学时）</strong><ul><li>使用 Python 实现广度优先搜索、深度优先搜索和 A* 算法。</li><li>应用搜索算法解决简单的路径规划问题。</li></ul></li><li><strong>机器学习实践（4 学时）</strong><ul><li>使用 Python 的 Scikit - learn 库实现线性回归、逻辑回归和决策树算法。</li><li>对数据集进行特征提取、模型训练和评估。</li></ul></li><li><strong>深度学习实践（4 学时）</strong><ul><li>使用 Python 的 TensorFlow 或 PyTorch 库搭建简单的神经网络模型。</li><li>进行图像识别和文本分类的实践。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>包括线上学习情况（视频观看、作业完成）、课堂表现（出勤、回答问题）和小组讨论成绩。</li></ul></li><li><strong>实验成绩（20%）</strong><ul><li>根据学生在实践教学中的实验报告和操作表现进行评分。</li></ul></li><li><strong>期末考试（50%）</strong><ul><li>采用闭卷考试的方式，考查学生对人工智能基本概念、算法和模型的掌握程度。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《人工智能：一种现代的方法》（第 4 版），Stuart Russell 和 Peter Norvig 著，清华大学出版社。</li><li>《机器学习》，周志华著，清华大学出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《深度学习》，Ian Goodfellow、Yoshua Bengio 和 Aaron Courville 著，人民邮电出版社。</li><li>《Python 机器学习》（第 3 版），Sebastian Raschka 和 Vahid Mirjalili 著，机械工业出版社。</li><li>相关学术论文和在线课程，如 Coursera 上的 “人工智能基础”、edX 上的 “机器学习” 等。</li></ul></li></ul><h3>先修课程要求</h3><ul><li>高等数学、线性代数、概率论与数理统计。</li><li>编程语言基础，如 Python 或 Java。</li></ul><p><button><path></path>代码文件</button></p><p><button><path></path>GitHub 仓库</button></p><p><br/></p>');
INSERT INTO `course` VALUES (5, 4, '计算机组成原理', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743090446849-v2-dac5bfcf4320627d7382756d787e9282_1440w.jpg', 'b3M4LGQW', '<ul><li><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：计算机组成原理</li><li><strong>课程代码</strong>：通常各高校有自己的编号体系，如 COMP102</li><li><strong>课程学分</strong>：一般为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学约 48 - 64 学时，实践教学约 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息安全等计算机相关专业的本科生</li><li><strong>授课方式</strong>：以课堂讲授为主，结合实验教学、在线资源学习、小组讨论等方式。课堂讲授通过多媒体课件、实物演示等手段讲解知识；实验教学让学生在实验室进行实际操作；在线资源供学生课后自主学习；小组讨论促进学生交流合作。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>深入理解计算机系统的基本组成结构，包括运算器、控制器、存储器、输入输出设备等。</li><li>掌握计算机中数据的表示和运算方法，如二进制、十进制、十六进制之间的转换，定点数和浮点数的表示与运算。</li><li>熟悉指令系统的设计原理和指令执行过程，了解不同类型指令的功能和作用。</li><li>了解计算机的存储系统层次结构，包括高速缓存、主存、外存等的工作原理和性能特点。</li><li>掌握计算机的输入输出系统的工作方式，如程序查询、中断、DMA 等。</li></ul><h4>能力目标</h4><ul><li>具备运用所学知识分析和设计简单计算机系统部件的能力，如设计一个简单的运算器或控制器。</li><li>能够进行计算机系统中数据的处理和运算，编写相关的算法和程序。</li><li>能够分析和解决计算机组成原理中的实际问题，如性能优化、故障排除等。</li><li>培养学生的创新思维和实践能力，通过实验和课程设计，提高学生的动手能力和团队协作能力。</li></ul><h4>素质目标</h4><ul><li>培养学生严谨的科学态度和逻辑思维能力，在学习和实践过程中注重细节和准确性。</li><li>增强学生的自主学习能力和终身学习意识，鼓励学生关注计算机领域的最新发展动态。</li><li>培养学生的职业道德和责任感，让学生了解计算机技术在社会中的应用和影响，遵守相关的法律法规和道德规范。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>计算机系统概述（4 - 6 学时）</strong><ul><li>计算机的发展历程，包括电子管计算机、晶体管计算机、集成电路计算机、大规模和超大规模集成电路计算机等阶段。</li><li>计算机系统的层次结构，从硬件层到软件层的各个层次及其功能。</li><li>计算机的分类和应用领域，如巨型机、大型机、中型机、小型机、微型机等，以及在科学计算、数据处理、过程控制、人工智能等领域的应用。</li></ul></li><li><strong>数据的表示和运算（8 - 10 学时）</strong><ul><li>数制与编码，包括二进制、十进制、十六进制的表示和转换，原码、反码、补码、移码的表示和运算。</li><li>定点数的表示和运算，如定点整数和定点小数的表示，加减法、乘除法运算。</li><li>浮点数的表示和运算，IEEE 754 标准浮点数的表示，浮点数的加减法运算。</li><li>字符编码，如 ASCII 码、Unicode 码等。</li></ul></li><li><strong>运算方法和运算器（6 - 8 学时）</strong><ul><li>算术逻辑单元（ALU）的设计和实现，包括基本的逻辑运算和算术运算。</li><li>定点运算器的组成和工作原理，如加法器、乘法器、除法器的设计。</li><li>浮点运算器的组成和工作原理，浮点加减法、乘除法的实现。</li></ul></li><li><strong>指令系统（8 - 10 学时）</strong><ul><li>指令格式和指令编码，如操作码、地址码的设计，指令的长度和扩展操作码技术。</li><li>寻址方式，包括立即寻址、直接寻址、间接寻址、寄存器寻址、寄存器间接寻址等。</li><li>指令类型，如数据传送指令、算术运算指令、逻辑运算指令、程序控制指令等。</li><li>指令系统的设计原则和发展趋势。</li></ul></li><li><strong>中央处理器（CPU）（10 - 12 学时）</strong><ul><li>CPU 的功能和组成，包括运算器、控制器、寄存器组等。</li><li>指令执行过程，如取指、译码、执行等阶段。</li><li>微程序控制器的设计和实现，微指令的格式和编码，微程序的设计和执行。</li><li>硬布线控制器的设计和实现，控制信号的产生和时序安排。</li><li>CPU 的性能指标和性能优化技术，如流水线技术、超标量技术等。</li></ul></li><li><strong>存储系统（10 - 12 学时）</strong><ul><li>存储系统的层次结构，高速缓存（Cache）、主存、外存的特点和作用。</li><li>主存储器的组织和管理，如存储芯片的连接、地址译码、存储容量的扩展等。</li><li>高速缓存的工作原理和映射方式，如直接映射、全相联映射、组相联映射等。</li><li>虚拟存储器的概念和实现方法，页式、段式、段页式虚拟存储器的工作原理。</li></ul></li><li><strong>输入输出系统（6 - 8 学时）</strong><ul><li>输入输出设备的分类和特点，如键盘、鼠标、显示器、打印机等。</li><li>输入输出接口的功能和组成，接口的寻址方式和数据传送方式。</li><li>程序查询方式、中断方式、DMA 方式的工作原理和特点。</li><li>通道和总线的概念和工作原理，通道的类型和总线的性能指标。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>实验环境搭建（2 - 4 学时）</strong><ul><li>熟悉实验设备和软件工具，如计算机组成原理实验箱、模拟器等。</li><li>掌握实验环境的配置和使用方法，如安装操作系统、实验软件等。</li></ul></li><li><strong>基本运算器设计实验（4 - 6 学时）</strong><ul><li>设计并实现一个简单的算术逻辑单元（ALU）。</li><li>进行定点数的加减法、乘除法运算实验。</li></ul></li><li><strong>指令系统实验（4 - 6 学时）</strong><ul><li>设计并实现一个简单的指令系统。</li><li>编写和调试指令程序，验证指令的功能。</li></ul></li><li><strong>存储系统实验（4 - 6 学时）</strong><ul><li>进行主存储器的扩展实验。</li><li>实现高速缓存的模拟实验，观察不同映射方式下的命中率。</li></ul></li><li><strong>输入输出系统实验（4 - 6 学时）</strong><ul><li>实现程序查询方式、中断方式的数据传送实验。</li><li>进行 DMA 方式的数据传送实验。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>包括课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试的方式，题型包括选择题、填空题、简答题、计算题、设计题等，全面考查学生对课程知识的掌握程度和应用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《计算机组成原理（第 5 版）》，唐朔飞主编，高等教育出版社。</li><li>《计算机组成与设计：硬件 / 软件接口（原书第 5 版）》，David A. Patterson、John L. Hennessy 著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《计算机组成原理学习指导与习题解答（第 2 版）》，唐朔飞主编，高等教育出版社。</li><li>《深入理解计算机系统（原书第 3 版）》，Randal E. Bryant、David R. O\'Hallaron 著，机械工业出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li>高等数学：为理解计算机中的数据运算和性能分析提供数学基础。</li><li>数字电路与逻辑设计：熟悉数字电路的基本原理和逻辑门的应用，为理解计算机硬件组成和工作原理奠定基础。</li><li>程序设计基础：掌握一种编程语言（如 C 语言），能够进行简单的程序设计和调试，有助于理解计算机软件和硬件的交互。</li></ul></li></ul>');
INSERT INTO `course` VALUES (6, 4, '数据结构算法分析', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743090608287-R-C.jfif', '0LabDoB0', '<ul><li><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：数据结构与算法分析</li><li><strong>课程代码</strong>：不同院校有不同设定，例如 CS203</li><li><strong>课程学分</strong>：通常为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息与计算科学等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、实验教学、在线学习、小组讨论相结合的方式。课堂讲授系统讲解理论知识；实验教学让学生进行实践操作；在线学习提供丰富的学习资源；小组讨论培养学生合作交流能力。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>深入理解各种数据结构的定义、特点和存储表示，如线性表、栈、队列、树、图等。</li><li>掌握不同数据结构上的基本操作，如插入、删除、查找、遍历等。</li><li>熟悉常见算法的设计策略和分析方法，如分治法、动态规划、贪心算法等。</li><li>了解算法复杂度分析的概念和方法，能够分析算法的时间复杂度和空间复杂度。</li></ul><h4>能力目标</h4><ul><li>具备运用数据结构和算法知识解决实际问题的能力，能够针对具体问题选择合适的数据结构和算法进行设计和实现。</li><li>提高编程能力，能够使用至少一种编程语言（如 Python、Java 等）实现各种数据结构和算法。</li><li>培养算法设计和分析能力，能够设计高效的算法并对其性能进行评估和优化。</li><li>提升逻辑思维和抽象思维能力，通过对数据结构和算法的学习，增强对问题的抽象和建模能力。</li></ul><h4>素质目标</h4><ul><li>培养严谨的科学态度和创新精神，在算法设计和实现过程中注重细节和正确性，鼓励创新思维。</li><li>增强团队协作和沟通能力，通过小组讨论和项目实践，提高学生的合作能力和交流能力。</li><li>树立正确的知识产权意识，尊重他人的劳动成果，在学习和实践中遵守相关法律法规。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>绪论（2 - 4 学时）</strong><ul><li>数据结构的基本概念，包括数据、数据元素、数据对象、数据结构等。</li><li>算法的定义、特性和描述方法，如自然语言、流程图、伪代码等。</li><li>算法复杂度分析，时间复杂度和空间复杂度的概念、计算方法和渐进表示法。</li></ul></li><li><strong>线性表（6 - 8 学时）</strong><ul><li>线性表的定义和基本操作，如初始化、插入、删除、查找等。</li><li>线性表的顺序存储结构和链式存储结构，包括单链表、循环链表、双向链表等。</li><li>线性表的应用，如多项式的表示和运算、约瑟夫环问题等。</li></ul></li><li><strong>栈和队列（6 - 8 学时）</strong><ul><li>栈的定义、特点和基本操作，如进栈、出栈、判空等。</li><li>栈的顺序存储结构和链式存储结构，栈在表达式求值、递归调用等方面的应用。</li><li>队列的定义、特点和基本操作，如入队、出队、判空等。</li><li>队列的顺序存储结构和链式存储结构，循环队列的实现和应用。</li></ul></li><li><strong>串（4 - 6 学时）</strong><ul><li>串的定义、基本操作和存储结构，如顺序存储、链式存储等。</li><li>串的模式匹配算法，如朴素模式匹配算法、KMP 算法等。</li></ul></li><li><strong>数组和广义表（4 - 6 学时）</strong><ul><li>数组的定义、存储结构和基本操作，多维数组的存储方式。</li><li>特殊矩阵的压缩存储，如对称矩阵、三角矩阵、稀疏矩阵等。</li><li>广义表的定义、存储结构和基本操作，广义表的递归特性和应用。</li></ul></li><li><strong>树和二叉树（8 - 10 学时）</strong><ul><li>树的基本概念、术语和存储结构，如双亲表示法、孩子表示法、孩子兄弟表示法等。</li><li>二叉树的定义、性质和存储结构，如顺序存储、链式存储等。</li><li>二叉树的遍历算法，如前序遍历、中序遍历、后序遍历、层序遍历等。</li><li>线索二叉树的概念和实现，哈夫曼树的构造和应用。</li><li>树和森林与二叉树的转换，树的遍历算法。</li></ul></li><li><strong>图（8 - 10 学时）</strong><ul><li>图的基本概念、术语和存储结构，如邻接矩阵、邻接表等。</li><li>图的遍历算法，如深度优先搜索（DFS）和广度优先搜索（BFS）。</li><li>图的连通性问题，如最小生成树算法（Prim 算法、Kruskal 算法）。</li><li>最短路径问题，如 Dijkstra 算法、Floyd 算法。</li><li>拓扑排序和关键路径算法。</li></ul></li><li><strong>查找（6 - 8 学时）</strong><ul><li>查找的基本概念和性能指标，如平均查找长度（ASL）。</li><li>静态查找表，如顺序查找、折半查找、分块查找等。</li><li>动态查找表，如二叉排序树、平衡二叉树（AVL 树）等。</li><li>哈希表的概念、构造方法和处理冲突的方法，如开放定址法、链地址法等。</li></ul></li><li><strong>排序（8 - 10 学时）</strong><ul><li>排序的基本概念和分类，如内部排序和外部排序。</li><li>插入排序，如直接插入排序、希尔排序等。</li><li>交换排序，如冒泡排序、快速排序等。</li><li>选择排序，如简单选择排序、堆排序等。</li><li>归并排序和基数排序。</li><li>各种排序算法的性能比较和应用场景。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>线性表实验（4 - 6 学时）</strong><ul><li>实现顺序表和单链表的基本操作，如插入、删除、查找等。</li><li>解决线性表的应用问题，如多项式的相加。</li></ul></li><li><strong>栈和队列实验（4 - 6 学时）</strong><ul><li>实现栈和队列的顺序存储结构和链式存储结构。</li><li>利用栈和队列解决实际问题，如表达式求值、迷宫求解。</li></ul></li><li><strong>树和二叉树实验（4 - 6 学时）</strong><ul><li>实现二叉树的链式存储结构和遍历算法。</li><li>构造哈夫曼树并进行编码和解码。</li></ul></li><li><strong>图实验（4 - 6 学时）</strong><ul><li>实现图的邻接矩阵和邻接表存储结构。</li><li>实现图的遍历算法和最小生成树算法。</li></ul></li><li><strong>查找和排序实验（4 - 6 学时）</strong><ul><li>实现各种查找算法和排序算法。</li><li>比较不同查找算法和排序算法的性能。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>涵盖课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试，题型有选择题、填空题、简答题、算法设计题、算法分析题等，全面考查学生对知识的掌握和运用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《数据结构（C 语言版）》，严蔚敏、吴伟民编著，清华大学出版社。</li><li>《数据结构与算法分析 ——C 语言描述（第 2 版）》，Mark Allen Weiss 著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《算法导论（原书第 3 版）》，Thomas H. Cormen 等著，机械工业出版社。</li><li>《Python 数据结构与算法分析（第 2 版）》，Brad Miller、David Ranum 著，人民邮电出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 C、Python 或 Java），能够进行基本的程序设计和调试，理解变量、数据类型、控制结构、函数等概念。</li><li><strong>离散数学</strong>：了解集合、逻辑、关系、图论等离散数学知识，为数据结构和算法的学习提供数学基础。</li></ul></li></ul>');
INSERT INTO `course` VALUES (7, 5, '数据库系统原理', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743090705481-2f99ef80112645c789034cfd1d3618b4.png', 'xkze0rrE', '<ol><li><ul><li><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：数据库系统原理</li><li><strong>课程代码</strong>：不同学校有不同的编码方式，例如 CS302</li><li><strong>课程学分</strong>：一般为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息管理与信息系统等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、实验教学、案例分析、小组讨论相结合的方式。课堂讲授系统传授理论知识；实验教学让学生进行数据库设计和操作实践；案例分析结合实际项目加深理解；小组讨论促进学生交流合作。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>理解数据库系统的基本概念、发展历程和主要组成部分。</li><li>掌握数据模型的相关知识，包括概念模型、逻辑模型和物理模型。</li><li>熟悉关系数据库的基本理论，如关系代数、关系演算等。</li><li>了解数据库设计的方法和步骤，能够进行数据库的需求分析、概念设计、逻辑设计和物理设计。</li><li>掌握数据库管理系统（DBMS）的基本功能和使用方法，如数据定义、数据操纵、数据控制等。</li></ul><h4>能力目标</h4><ul><li>具备运用数据库知识设计和实现小型数据库应用系统的能力。</li><li>能够使用 SQL 语言进行数据库的创建、表的定义、数据的插入、查询、更新和删除等操作。</li><li>能够分析和解决数据库系统中的常见问题，如数据完整性、并发控制、数据库恢复等。</li><li>培养学生的逻辑思维和抽象思维能力，通过对数据库结构和操作的学习，提高对数据的组织和管理能力。</li></ul><h4>素质目标</h4><ul><li>培养学生严谨的科学态度和责任心，在数据库设计和操作过程中确保数据的准确性和安全性。</li><li>增强学生的团队协作和沟通能力，通过小组项目实践，提高学生的合作能力和交流能力。</li><li>树立正确的知识产权意识，尊重他人的数据库成果，在学习和实践中遵守相关法律法规。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>数据库系统概述（4 - 6 学时）</strong><ul><li>数据库的基本概念，如数据、数据库、数据库管理系统、数据库系统等。</li><li>数据库系统的发展历程，包括层次数据库、网状数据库、关系数据库、面向对象数据库等阶段。</li><li>数据库系统的组成，包括硬件、软件、人员和数据等。</li><li>数据库系统的特点和优势，如数据共享、数据独立性、数据冗余度低等。</li></ul></li><li><strong>数据模型（6 - 8 学时）</strong><ul><li>概念模型，如实体 - 联系（E - R）模型，包括实体、属性、联系的概念和表示方法。</li><li>逻辑模型，如关系模型、层次模型、网状模型等，重点介绍关系模型的基本概念、关系的完整性约束等。</li><li>物理模型，了解数据在计算机中的存储方式和组织形式。</li></ul></li><li><strong>关系数据库（8 - 10 学时）</strong><ul><li>关系代数，包括传统的集合运算（并、差、交、笛卡尔积）和专门的关系运算（选择、投影、连接、除）。</li><li>关系演算，包括元组关系演算和域关系演算。</li><li>关系数据库标准语言 SQL，包括数据定义语言（DDL）、数据操纵语言（DML）和数据控制语言（DCL）。</li></ul></li><li><strong>数据库设计（8 - 10 学时）</strong><ul><li>数据库设计的基本步骤，包括需求分析、概念设计、逻辑设计、物理设计、数据库实施和数据库运行与维护。</li><li>需求分析的方法和工具，如数据流图、数据字典等。</li><li>概念设计的方法，如 E - R 图的设计和优化。</li><li>逻辑设计的方法，如将 E - R 图转换为关系模式，关系模式的规范化理论。</li><li>物理设计的内容，如存储结构的选择、索引的设计等。</li></ul></li><li><strong>数据库管理系统（6 - 8 学时）</strong><ul><li>数据库管理系统的功能和组成，如数据定义、数据操纵、数据控制、数据库维护等。</li><li>数据库的存储结构和存取方法，如文件组织、索引技术等。</li><li>数据库的安全性和完整性控制，如用户认证、授权机制、完整性约束等。</li><li>数据库的并发控制和恢复技术，如封锁协议、事务管理、故障恢复策略等。</li></ul></li><li><strong>数据库新技术（4 - 6 学时）</strong><ul><li>分布式数据库，包括分布式数据库的概念、特点、体系结构和数据分配策略。</li><li>数据仓库和数据挖掘，包括数据仓库的概念、体系结构、数据挖掘的方法和应用。</li><li>面向对象数据库和 XML 数据库，了解面向对象数据库的基本概念和 XML 数据库的特点和应用。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>SQL 语言实践（4 - 6 学时）</strong><ul><li>使用 SQL 语言进行数据库的创建、表的定义、数据的插入、查询、更新和删除等操作。</li><li>掌握 SQL 语言的高级功能，如视图、存储过程、触发器等的使用。</li></ul></li><li><strong>数据库设计实践（4 - 6 学时）</strong><ul><li>根据给定的需求，进行数据库的需求分析、概念设计、逻辑设计和物理设计。</li><li>使用数据库设计工具（如 PowerDesigner）进行数据库设计，并生成相应的数据库脚本。</li></ul></li><li><strong>数据库应用系统开发实践（4 - 6 学时）</strong><ul><li>选择一种编程语言（如 Python、Java 等）和数据库管理系统（如 MySQL、Oracle 等），开发一个小型的数据库应用系统。</li><li>实现数据库应用系统的基本功能，如用户登录、数据查询、数据添加、数据修改和数据删除等。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>包括课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试的方式，题型包括选择题、填空题、简答题、设计题、分析题等，全面考查学生对数据库系统原理的掌握程度和应用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《数据库系统概论（第 5 版）》，萨师煊、王珊编著，高等教育出版社。</li><li>《数据库系统概念（原书第 6 版）》，Abraham Silberschatz 等著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《SQL 必知必会（第 4 版）》，Ben Forta 著，人民邮电出版社。</li><li>《数据挖掘：概念与技术（第 3 版）》，Jiawei Han 等著，机械工业出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 C、Python 或 Java），能够进行基本的程序设计和调试，理解变量、数据类型、控制结构、函数等概念。</li><li><strong>离散数学</strong>：了解集合、逻辑、关系等离散数学知识，为关系数据库的学习提供数学基础。</li></ul></li></ul></li></ol>');
INSERT INTO `course` VALUES (8, 5, '软件工程项目管理', '2025-03-27 00:00:00', '2026-01-01 00:00:00', 'http://localhost:9998/files/course/1743090988860-OIP-C.jfif', 'F6Gu9ALO', '<ul><li><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：数据结构与算法分析</li><li><strong>课程代码</strong>：不同院校有不同设定，例如 CS203</li><li><strong>课程学分</strong>：通常为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息与计算科学等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、实验教学、在线学习、小组讨论相结合的方式。课堂讲授系统讲解理论知识；实验教学让学生进行实践操作；在线学习提供丰富的学习资源；小组讨论培养学生合作交流能力。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>深入理解各种数据结构的定义、特点和存储表示，如线性表、栈、队列、树、图等。</li><li>掌握不同数据结构上的基本操作，如插入、删除、查找、遍历等。</li><li>熟悉常见算法的设计策略和分析方法，如分治法、动态规划、贪心算法等。</li><li>了解算法复杂度分析的概念和方法，能够分析算法的时间复杂度和空间复杂度。</li></ul><h4>能力目标</h4><ul><li>具备运用数据结构和算法知识解决实际问题的能力，能够针对具体问题选择合适的数据结构和算法进行设计和实现。</li><li>提高编程能力，能够使用至少一种编程语言（如 Python、Java 等）实现各种数据结构和算法。</li><li>培养算法设计和分析能力，能够设计高效的算法并对其性能进行评估和优化。</li><li>提升逻辑思维和抽象思维能力，通过对数据结构和算法的学习，增强对问题的抽象和建模能力。</li></ul><h4>素质目标</h4><ul><li>培养严谨的科学态度和创新精神，在算法设计和实现过程中注重细节和正确性，鼓励创新思维。</li><li>增强团队协作和沟通能力，通过小组讨论和项目实践，提高学生的合作能力和交流能力。</li><li>树立正确的知识产权意识，尊重他人的劳动成果，在学习和实践中遵守相关法律法规。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>绪论（2 - 4 学时）</strong><ul><li>数据结构的基本概念，包括数据、数据元素、数据对象、数据结构等。</li><li>算法的定义、特性和描述方法，如自然语言、流程图、伪代码等。</li><li>算法复杂度分析，时间复杂度和空间复杂度的概念、计算方法和渐进表示法。</li></ul></li><li><strong>线性表（6 - 8 学时）</strong><ul><li>线性表的定义和基本操作，如初始化、插入、删除、查找等。</li><li>线性表的顺序存储结构和链式存储结构，包括单链表、循环链表、双向链表等。</li><li>线性表的应用，如多项式的表示和运算、约瑟夫环问题等。</li></ul></li><li><strong>栈和队列（6 - 8 学时）</strong><ul><li>栈的定义、特点和基本操作，如进栈、出栈、判空等。</li><li>栈的顺序存储结构和链式存储结构，栈在表达式求值、递归调用等方面的应用。</li><li>队列的定义、特点和基本操作，如入队、出队、判空等。</li><li>队列的顺序存储结构和链式存储结构，循环队列的实现和应用。</li></ul></li><li><strong>串（4 - 6 学时）</strong><ul><li>串的定义、基本操作和存储结构，如顺序存储、链式存储等。</li><li>串的模式匹配算法，如朴素模式匹配算法、KMP 算法等。</li></ul></li><li><strong>数组和广义表（4 - 6 学时）</strong><ul><li>数组的定义、存储结构和基本操作，多维数组的存储方式。</li><li>特殊矩阵的压缩存储，如对称矩阵、三角矩阵、稀疏矩阵等。</li><li>广义表的定义、存储结构和基本操作，广义表的递归特性和应用。</li></ul></li><li><strong>树和二叉树（8 - 10 学时）</strong><ul><li>树的基本概念、术语和存储结构，如双亲表示法、孩子表示法、孩子兄弟表示法等。</li><li>二叉树的定义、性质和存储结构，如顺序存储、链式存储等。</li><li>二叉树的遍历算法，如前序遍历、中序遍历、后序遍历、层序遍历等。</li><li>线索二叉树的概念和实现，哈夫曼树的构造和应用。</li><li>树和森林与二叉树的转换，树的遍历算法。</li></ul></li><li><strong>图（8 - 10 学时）</strong><ul><li>图的基本概念、术语和存储结构，如邻接矩阵、邻接表等。</li><li>图的遍历算法，如深度优先搜索（DFS）和广度优先搜索（BFS）。</li><li>图的连通性问题，如最小生成树算法（Prim 算法、Kruskal 算法）。</li><li>最短路径问题，如 Dijkstra 算法、Floyd 算法。</li><li>拓扑排序和关键路径算法。</li></ul></li><li><strong>查找（6 - 8 学时）</strong><ul><li>查找的基本概念和性能指标，如平均查找长度（ASL）。</li><li>静态查找表，如顺序查找、折半查找、分块查找等。</li><li>动态查找表，如二叉排序树、平衡二叉树（AVL 树）等。</li><li>哈希表的概念、构造方法和处理冲突的方法，如开放定址法、链地址法等。</li></ul></li><li><strong>排序（8 - 10 学时）</strong><ul><li>排序的基本概念和分类，如内部排序和外部排序。</li><li>插入排序，如直接插入排序、希尔排序等。</li><li>交换排序，如冒泡排序、快速排序等。</li><li>选择排序，如简单选择排序、堆排序等。</li><li>归并排序和基数排序。</li><li>各种排序算法的性能比较和应用场景。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>线性表实验（4 - 6 学时）</strong><ul><li>实现顺序表和单链表的基本操作，如插入、删除、查找等。</li><li>解决线性表的应用问题，如多项式的相加。</li></ul></li><li><strong>栈和队列实验（4 - 6 学时）</strong><ul><li>实现栈和队列的顺序存储结构和链式存储结构。</li><li>利用栈和队列解决实际问题，如表达式求值、迷宫求解。</li></ul></li><li><strong>树和二叉树实验（4 - 6 学时）</strong><ul><li>实现二叉树的链式存储结构和遍历算法。</li><li>构造哈夫曼树并进行编码和解码。</li></ul></li><li><strong>图实验（4 - 6 学时）</strong><ul><li>实现图的邻接矩阵和邻接表存储结构。</li><li>实现图的遍历算法和最小生成树算法。</li></ul></li><li><strong>查找和排序实验（4 - 6 学时）</strong><ul><li>实现各种查找算法和排序算法。</li><li>比较不同查找算法和排序算法的性能。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>涵盖课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试，题型有选择题、填空题、简答题、算法设计题、算法分析题等，全面考查学生对知识的掌握和运用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《数据结构（C 语言版）》，严蔚敏、吴伟民编著，清华大学出版社。</li><li>《数据结构与算法分析 ——C 语言描述（第 2 版）》，Mark Allen Weiss 著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《算法导论（原书第 3 版）》，Thomas H. Cormen 等著，机械工业出版社。</li><li>《Python 数据结构与算法分析（第 2 版）》，Brad Miller、David Ranum 著，人民邮电出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 C、Python 或 Java），能够进行基本的程序设计和调试，理解变量、数据类型、控制结构、函数等概念。</li><li><strong>离散数学</strong>：了解集合、逻辑、关系、图论等离散数学知识，为数据结构和算法的学习提供数学基础。</li></ul><p><button><path></path></button></p><button><path></path></button><button><path></path>分享</button><button><path></path></button><button><path></path></button><button><path></path></button><p>数据库系统原理的详细介绍</p><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：数据库系统原理</li><li><strong>课程代码</strong>：不同学校有不同的编码方式，例如 CS302</li><li><strong>课程学分</strong>：一般为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息管理与信息系统等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、实验教学、案例分析、小组讨论相结合的方式。课堂讲授系统传授理论知识；实验教学让学生进行数据库设计和操作实践；案例分析结合实际项目加深理解；小组讨论促进学生交流合作。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>理解数据库系统的基本概念、发展历程和主要组成部分。</li><li>掌握数据模型的相关知识，包括概念模型、逻辑模型和物理模型。</li><li>熟悉关系数据库的基本理论，如关系代数、关系演算等。</li><li>了解数据库设计的方法和步骤，能够进行数据库的需求分析、概念设计、逻辑设计和物理设计。</li><li>掌握数据库管理系统（DBMS）的基本功能和使用方法，如数据定义、数据操纵、数据控制等。</li></ul><h4>能力目标</h4><ul><li>具备运用数据库知识设计和实现小型数据库应用系统的能力。</li><li>能够使用 SQL 语言进行数据库的创建、表的定义、数据的插入、查询、更新和删除等操作。</li><li>能够分析和解决数据库系统中的常见问题，如数据完整性、并发控制、数据库恢复等。</li><li>培养学生的逻辑思维和抽象思维能力，通过对数据库结构和操作的学习，提高对数据的组织和管理能力。</li></ul><h4>素质目标</h4><ul><li>培养学生严谨的科学态度和责任心，在数据库设计和操作过程中确保数据的准确性和安全性。</li><li>增强学生的团队协作和沟通能力，通过小组项目实践，提高学生的合作能力和交流能力。</li><li>树立正确的知识产权意识，尊重他人的数据库成果，在学习和实践中遵守相关法律法规。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>数据库系统概述（4 - 6 学时）</strong><ul><li>数据库的基本概念，如数据、数据库、数据库管理系统、数据库系统等。</li><li>数据库系统的发展历程，包括层次数据库、网状数据库、关系数据库、面向对象数据库等阶段。</li><li>数据库系统的组成，包括硬件、软件、人员和数据等。</li><li>数据库系统的特点和优势，如数据共享、数据独立性、数据冗余度低等。</li></ul></li><li><strong>数据模型（6 - 8 学时）</strong><ul><li>概念模型，如实体 - 联系（E - R）模型，包括实体、属性、联系的概念和表示方法。</li><li>逻辑模型，如关系模型、层次模型、网状模型等，重点介绍关系模型的基本概念、关系的完整性约束等。</li><li>物理模型，了解数据在计算机中的存储方式和组织形式。</li></ul></li><li><strong>关系数据库（8 - 10 学时）</strong><ul><li>关系代数，包括传统的集合运算（并、差、交、笛卡尔积）和专门的关系运算（选择、投影、连接、除）。</li><li>关系演算，包括元组关系演算和域关系演算。</li><li>关系数据库标准语言 SQL，包括数据定义语言（DDL）、数据操纵语言（DML）和数据控制语言（DCL）。</li></ul></li><li><strong>数据库设计（8 - 10 学时）</strong><ul><li>数据库设计的基本步骤，包括需求分析、概念设计、逻辑设计、物理设计、数据库实施和数据库运行与维护。</li><li>需求分析的方法和工具，如数据流图、数据字典等。</li><li>概念设计的方法，如 E - R 图的设计和优化。</li><li>逻辑设计的方法，如将 E - R 图转换为关系模式，关系模式的规范化理论。</li><li>物理设计的内容，如存储结构的选择、索引的设计等。</li></ul></li><li><strong>数据库管理系统（6 - 8 学时）</strong><ul><li>数据库管理系统的功能和组成，如数据定义、数据操纵、数据控制、数据库维护等。</li><li>数据库的存储结构和存取方法，如文件组织、索引技术等。</li><li>数据库的安全性和完整性控制，如用户认证、授权机制、完整性约束等。</li><li>数据库的并发控制和恢复技术，如封锁协议、事务管理、故障恢复策略等。</li></ul></li><li><strong>数据库新技术（4 - 6 学时）</strong><ul><li>分布式数据库，包括分布式数据库的概念、特点、体系结构和数据分配策略。</li><li>数据仓库和数据挖掘，包括数据仓库的概念、体系结构、数据挖掘的方法和应用。</li><li>面向对象数据库和 XML 数据库，了解面向对象数据库的基本概念和 XML 数据库的特点和应用。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>SQL 语言实践（4 - 6 学时）</strong><ul><li>使用 SQL 语言进行数据库的创建、表的定义、数据的插入、查询、更新和删除等操作。</li><li>掌握 SQL 语言的高级功能，如视图、存储过程、触发器等的使用。</li></ul></li><li><strong>数据库设计实践（4 - 6 学时）</strong><ul><li>根据给定的需求，进行数据库的需求分析、概念设计、逻辑设计和物理设计。</li><li>使用数据库设计工具（如 PowerDesigner）进行数据库设计，并生成相应的数据库脚本。</li></ul></li><li><strong>数据库应用系统开发实践（4 - 6 学时）</strong><ul><li>选择一种编程语言（如 Python、Java 等）和数据库管理系统（如 MySQL、Oracle 等），开发一个小型的数据库应用系统。</li><li>实现数据库应用系统的基本功能，如用户登录、数据查询、数据添加、数据修改和数据删除等。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>包括课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、编程作业）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试的方式，题型包括选择题、填空题、简答题、设计题、分析题等，全面考查学生对数据库系统原理的掌握程度和应用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《数据库系统概论（第 5 版）》，萨师煊、王珊编著，高等教育出版社。</li><li>《数据库系统概念（原书第 6 版）》，Abraham Silberschatz 等著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《SQL 必知必会（第 4 版）》，Ben Forta 著，人民邮电出版社。</li><li>《数据挖掘：概念与技术（第 3 版）》，Jiawei Han 等著，机械工业出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 C、Python 或 Java），能够进行基本的程序设计和调试，理解变量、数据类型、控制结构、函数等概念。</li><li><strong>离散数学</strong>：了解集合、逻辑、关系等离散数学知识，为关系数据库的学习提供数学基础。</li></ul><p>软件工程与项目管理详细介绍</p><h3>课程基本信息</h3><ul><li><strong>课程名称</strong>：软件工程与项目管理</li><li><strong>课程代码</strong>：不同院校设置不同，例如 SE401</li><li><strong>课程学分</strong>：一般为 3 - 4 学分</li><li><strong>课程学时</strong>：理论教学 48 - 64 学时，实践教学 16 - 32 学时</li><li><strong>授课对象</strong>：计算机科学与技术、软件工程、信息管理等专业本科生</li><li><strong>授课方式</strong>：采用课堂讲授、案例分析、小组项目实践、在线学习相结合的方式。课堂讲授系统讲解理论知识；案例分析结合实际项目加深理解；小组项目实践让学生亲身体验软件开发流程；在线学习提供丰富的拓展资源。</li></ul><h3>课程目标</h3><h4>知识目标</h4><ul><li>理解软件工程的基本概念、原理和方法，包括软件生命周期、软件开发模型等。</li><li>掌握软件需求分析、设计、编码、测试和维护等阶段的技术和方法。</li><li>熟悉软件项目管理的基本内容，如项目计划、进度控制、成本估算、风险管理等。</li><li>了解软件质量保证和软件配置管理的相关知识。</li></ul><h4>能力目标</h4><ul><li>具备运用软件工程方法和技术进行软件开发的能力，能够参与小型软件项目的开发。</li><li>掌握软件项目管理的基本技能，能够制定项目计划、监控项目进度、管理项目成本。</li><li>提高团队协作和沟通能力，能够在项目团队中有效地进行交流和合作。</li><li>培养问题解决能力，能够分析和解决软件开发和项目管理过程中遇到的问题。</li></ul><h4>素质目标</h4><ul><li>培养严谨的科学态度和职业道德，确保软件开发的质量和可靠性。</li><li>增强创新意识和学习能力，关注软件工程领域的最新发展动态。</li><li>树立全局观念和系统思维，从整体上把握软件项目的开发和管理。</li></ul><h3>课程内容</h3><h4>理论教学</h4><ul><li><strong>软件工程概述（4 - 6 学时）</strong><ul><li>软件的概念、特点和分类，软件危机的产生和表现。</li><li>软件工程的定义、目标和原则，软件工程的发展历程。</li><li>软件生命周期的概念和主要阶段，常见的软件开发模型，如瀑布模型、敏捷开发模型、迭代模型等。</li></ul></li><li><strong>软件需求分析（6 - 8 学时）</strong><ul><li>需求分析的任务、过程和方法，需求获取的技术，如访谈、问卷调查、观察等。</li><li>需求规格说明书的编写，包括功能需求、性能需求、接口需求等。</li><li>需求建模的方法，如数据流图、实体 - 联系图、用例图等。</li></ul></li><li><strong>软件设计（8 - 10 学时）</strong><ul><li>软件设计的原则和方法，包括模块化设计、抽象与信息隐藏等。</li><li>软件体系结构设计，如分层结构、客户 - 服务器结构等。</li><li>详细设计的方法，如过程设计、数据结构设计等，常用的详细设计工具，如流程图、N - S 图等。</li></ul></li><li><strong>软件编码（4 - 6 学时）</strong><ul><li>程序设计语言的选择原则，常见的程序设计语言及其特点。</li><li>编码规范和编程风格，提高代码可读性和可维护性的方法。</li></ul></li><li><strong>软件测试（6 - 8 学时）</strong><ul><li>软件测试的目的、原则和方法，测试用例的设计技术，如等价类划分、边界值分析等。</li><li>软件测试的阶段和策略，如单元测试、集成测试、系统测试等。</li><li>软件调试的方法和技巧。</li></ul></li><li><strong>软件维护（4 - 6 学时）</strong><ul><li>软件维护的概念、类型和特点，维护过程和维护成本。</li><li>软件可维护性的概念和提高可维护性的方法。</li></ul></li><li><strong>软件项目管理（8 - 10 学时）</strong><ul><li>软件项目管理的概念、内容和重要性。</li><li>项目计划的制定，包括项目范围管理、进度管理、成本管理等。</li><li>项目进度控制的方法和技术，如甘特图、关键路径法等。</li><li>成本估算的方法，如类比估算法、参数估算法等。</li><li>风险管理的概念、过程和方法，风险识别、评估和应对策略。</li></ul></li><li><strong>软件质量保证与软件配置管理（4 - 6 学时）</strong><ul><li>软件质量的概念、度量和保证体系，软件质量控制的方法和技术。</li><li>软件配置管理的概念、任务和过程，配置项的标识、版本控制等。</li></ul></li></ul><h4>实践教学</h4><ul><li><strong>软件项目需求分析实践（4 - 6 学时）</strong><ul><li>选择一个小型软件项目，进行需求调研和分析。</li><li>编写需求规格说明书，绘制需求模型图。</li></ul></li><li><strong>软件设计实践（4 - 6 学时）</strong><ul><li>根据需求规格说明书，进行软件体系结构设计和详细设计。</li><li>绘制软件设计图，编写设计文档。</li></ul></li><li><strong>软件编码与测试实践（4 - 6 学时）</strong><ul><li>使用选定的编程语言进行软件编码实现。</li><li>设计测试用例，进行软件测试和调试。</li></ul></li><li><strong>软件项目管理实践（4 - 6 学时）</strong><ul><li>制定软件项目计划，包括项目进度计划、成本计划等。</li><li>监控项目进度，进行风险管理和质量控制。</li></ul></li></ul><h3>考核方式</h3><ul><li><strong>平时成绩（30%）</strong><ul><li>涵盖课堂表现（出勤、回答问题、参与讨论）、作业完成情况（书面作业、项目文档）、实验报告成绩等。</li></ul></li><li><strong>期末考试（70%）</strong><ul><li>采用闭卷考试形式，题型有选择题、填空题、简答题、论述题、案例分析题等，全面考查学生对软件工程与项目管理知识的掌握和应用能力。</li></ul></li></ul><h3>教材及参考资料</h3><ul><li><strong>教材</strong><ul><li>《软件工程（第 4 版）》，张海藩、牟永敏编著，人民邮电出版社。</li><li>《软件项目管理（第 5 版）》，Roger S. Pressman 著，机械工业出版社。</li></ul></li><li><strong>参考资料</strong><ul><li>《敏捷软件开发：原则、模式与实践》，Robert C. Martin 著，清华大学出版社。</li><li>《人月神话（ anniversary Edition）》，Frederick P. Brooks 著，清华大学出版社。</li></ul></li></ul><h3>先修课程要求</h3><ul><li><strong>程序设计基础</strong>：掌握至少一种编程语言（如 Java、Python 等），能够进行基本的程序设计和调试。</li><li><strong>数据结构与算法</strong>：了解常用的数据结构和算法，具备一定的算法设计和分析能力。</li></ul><p><button><path></path></button></p><button><path></path></button><button><path></path></button><button><path></path>分享</button><button><path></path></button><button><path></path></button><button><path></path></button><p><br/></p></li></ul>');
INSERT INTO `course` VALUES (9, 4, 'c/c++程序设计', '2025-04-15 00:00:00', '2026-04-15 00:00:00', 'http://localhost:9998/files/course/1744723086615-R-C.jfif', 'gZC4J7oZ', '<p>该课程教大家写c/c++代码</p>');

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `is_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否批准',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '课程学生关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_student
-- ----------------------------
INSERT INTO `course_student` VALUES (1, 5, 7, '同意');
INSERT INTO `course_student` VALUES (2, 4, 7, '同意');
INSERT INTO `course_student` VALUES (3, 7, 7, '同意');
INSERT INTO `course_student` VALUES (4, 8, 7, '同意');
INSERT INTO `course_student` VALUES (5, 6, 7, '同意');
INSERT INTO `course_student` VALUES (6, 5, 8, '同意');
INSERT INTO `course_student` VALUES (7, 4, 8, '同意');
INSERT INTO `course_student` VALUES (8, 7, 8, '同意');
INSERT INTO `course_student` VALUES (9, 4, 6, '同意');
INSERT INTO `course_student` VALUES (10, 5, 6, '同意');
INSERT INTO `course_student` VALUES (11, 7, 6, '同意');
INSERT INTO `course_student` VALUES (12, 8, 6, '同意');
INSERT INTO `course_student` VALUES (13, 6, 6, '同意');
INSERT INTO `course_student` VALUES (14, 8, 8, '同意');
INSERT INTO `course_student` VALUES (15, 6, 8, '同意');

-- ----------------------------
-- Table structure for exam_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_answer`;
CREATE TABLE `exam_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考生答题id',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '试卷id',
  `paper_id` int(11) NULL DEFAULT NULL COMMENT '问题id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '考生id',
  `answer` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '问题答案',
  `mark_exams` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '教师批阅',
  `ring_sign` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '环签名',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '单题提交答案时间',
  `is_marked` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '标记',
  `right_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '验签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '学生答题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_answer
-- ----------------------------
INSERT INTO `exam_answer` VALUES (27, 10, 40, 6, 'B', '2', '83789618764184301301595365456478919557724377309855646198521630483513144569575,17287993732967803076864682840265042915869143746552908324704571002347558472166,913514672243398744210470293330810942834905254917029663903610930629575710493,54720886205491757012575239898177005741183371737981707296273861843525195708678,29544923187084156865693060103672594323328521888389623360744744680815424385271,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:39:12', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (28, 10, 41, 6, 'C', '0', '51588529834899412208920917296833903256539389958409506695262302639418005219024,70667618316132420539954475309568179078064421394816990746072454816066919651532,14065091010816229839874477661975711336192661433673693656310426054862601193180,4290715772809799251081252379956795740278611750371401486947455927845282672591,28408111917798115482280473499065096793827291493466063451329710701208015048301,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:39:24', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (29, 10, 42, 6, 'D', '0', '6962752876829205002253448592267348897141913987100179107888079283260237968827,4344000316978196844144190631195707799758791944134158079999095589761427135016,10892375852856625999352553441982469635982562358930971547169933562120709468690,44928155814453951460672093278284837719703547724960872213016703141442623200625,37650586639410322194860826521995752899497554746926114393435281433882948501652,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:39:48', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (30, 10, 43, 6, 'B', '2', '107232701839201051290326873538880187361303634199373599511823997958344752302691,1733822059858437047632391630749957507634283097507035294359012132938676059797,53908778869270648506761493184486100695019131614520511843611197998300114605297,42164279532493990322462545981836852889980699945026927138685093782923473652782,6021336606237350195498245718183003957368789992621635038272875729661455715929,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:40:11', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (31, 10, 44, 6, 'B', '2', '29328005623067001311988225140544738106604913613715942080123587399901703205128,20429669904151513337503340083626910276787516008191928458192572807177907175908,13248079427028270584799664072486498218571572275844728996382174973025523207503,34371584389213623349812447605207630456327601655160490334767611768928345837493,20454173968898703035288669767714738556992540893907444207663477805673214798592,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:40:47', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (32, 10, 53, 6, '栈顶 队尾 队首', '2', '17914558730694350622571948638412732010179523736958842541906537460878059975988,3317281155599546085523683063811100929045325143621488131775922670382493436564,11607506144212543012758097558183982573610478029053707363096965807513927926097,24978230033981080389856599422169608397099302827896264930199902060645524794932,31911744876669265749376630056319209486141579447150696136138273058047980337287,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:42:21', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (33, 10, 54, 6, NULL, '2', '53334785276068329237350383414018825602178941953677079830911485575231421563383,10907418772690623728804328959646089139088550087533059780513834111801006243895,42828785624473374887513960405360981746076876791847266317680931644498891480425,10120958806883070690256687636243738479147127608834433128039756237834847466597,6713961663554534985160586404960254870499453841454858987569361284783042215794,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:42:38', '是', '验签成功');
INSERT INTO `exam_answer` VALUES (34, 10, 45, 6, 'T', '2', '147336966562738727782931511587351469649796460733978786172199515212883052899,17944937601662710584475909364492615088637415235290966866490625024879256330829,58603398515303305368785559625709746179836812500966543649227254926212719061643,3557419479160166859347969343710792132618752223266484329600063962586417152628,22036602905740688377255575227963146917740110825165238012530670112868907031906,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:42:54', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (35, 10, 46, 6, 'T', '2', '61027093893742512346820678340292649213029647320294341827423788507192463366176,20152401813765453739243406800166210403250939039463124221444129000855422983356,4565826290723497831811537377719178476480075435073685395264819922800159419057,34337499989248373407516436145199645027638126341381197227341463071476843213650,18931978542000082610640397785500585069547505602724078114035201722005388732255,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:43:01', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (36, 10, 47, 6, 'F', '0', '49920831813501574841923772392221023209743880533979827451540544608381075099320,73018050084388089831718718315476184642080219088876717443216803239537275943766,57338147382957684686855048929280057592738834371949583815031419260202934056145,14304199370932353683125414125398157509205197805348561098993095427618918480962,30538374543701168881583618940608235913110806550639347277368132248508852943244,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:43:17', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (37, 10, 48, 6, 'T', '2', '28761483277775738035013752709350926498033589935093402281320742213590212087017,45561179812946943742404979039709818286799223915675949413664322538427932299731,77211952212477063964512557486638627552819994423890984023489649976958334019917,48488668255898921905155590882859080876812528956580705103427399307058944870864,57012300220911388483307652236963744341499802289279398999590795125770821583180,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:43:24', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (38, 10, 49, 6, 'F', '2', '60294359961328100492594902808646812441754624326411662840521267265424611015869,9934065332211070708584297856090200764724749498314780321024849987789554588549,281538407563833220902969433492278085610836863465507589506377866149693807398,42348435137136513160535472154345710802340512737334175159595668425669248465285,10241381104775912405066238507880858896009307938724440770831017899082285193926,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:43:35', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (39, 10, 60, 6, '#include<stdio.h>\r\nint main(){\r\n    printf(\"hello\");\r\n}', '5', '16234247672310929953654691170362022225279529877358146094031339838703574821563,19339979253189806887693172823855018330708883980292038221218994177252202233728,67290533380633425798563560793248448392696065365772341368004566626028955502010,24542511677029233176533440561490477563531111173664493798562520297428293299828,35939647209266918570279697927109113694645521468362116857753083722291646095473,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:46:39', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (40, 10, 40, 7, 'B', '2', '69287520395139283538453353451553348106193783864292953868820101706679486017463,7533536650586492644927606650201131710501427001807788303554202594592985316636,29391286751716732418057838799942063304155040700833873507585123236957987110345,49545692690816041590566770299518985671129574746153554494502894482361259891573,33384804705585497712523505599200812842774135567316947279102052386801173900988,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:54:42', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (41, 10, 41, 7, 'C', '0', '10296453777730488486804544954513104257348836319454956892416966064201698344740,54681607600832224484294252483887471823598230407688734105799791494860071164175,36527905992918824832738961458779637726026997632309563110594336940695117886330,54441520961484832208711450563435973082782979561399713829788067428838429739426,45323969905421255325596769577265857630205904989512325783307589172540135106553,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:54:55', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (42, 10, 42, 7, 'D', '0', '27592753838529824004733011179175535930007261338759787787503839504223971115394,44482815826467024876521947379082488097238252286640385822002641374660783747941,8155220977488763437338054724732602822577223109700558974936024932270748801094,8305426517462648117634267528000092914947793017739335240789498406742493259538,45229711412949488574574292244497179674294569110692576610767379805229760995483,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:56:13', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (43, 10, 43, 7, 'C', '0', '21847405865017852597404750419551109250754777431881690044803944530555210695581,14951015000333181220106178286565395589724891946052782592927639521461410300706,45278945761359833494375873779985198833341416603723367266746300305207483383674,7662875520688504684927583054111875709602934692401249986435222329743017619754,48812116137181692638192162831553201685890221866436250967145568431520777139131,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:56:18', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (44, 10, 44, 7, 'D', '0', '17776667539223532967430620044824484075213015071635051635205758429718351525002,75549735767522994965290794492469076180970718295958136560171747944396562896665,28767434766888050338239533497625444595364865350943039598687859217442280767943,48111976606705743798764787267301049821304324707320421645138377170082786850361,49205839649660244160970281473793559195786070157269942096802949971523850168505,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:56:22', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (45, 10, 45, 7, 'T', '2', '1888767484561130539790124350039503101953898220853721868597569293510207031529,14689231366526656634307247215436207077962273214315234484967384610511329314277,18817251099640785254788753293250389292393132968177497781348580462628344710201,36618693752240403521113271637570125306123963516644960957027002081853416072103,9538855602614398035493866933800353154847035036549823710910259459632699436947,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:56:36', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (46, 10, 46, 7, 'T', '2', '98793858303010858005374316182998558429969500828650751132465443410534446496307,16115074097264090692066225446180943725111830022793510298495688468045136553084,56777983048882741919687941425481471505847764114078546001240804050829323933932,33934840853462941721386640676943070576058486692443267769135306959432416861841,2468548033968536000082680753373291829959667876555633115516576113901859663481,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:56:43', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (47, 10, 47, 7, 'T', '2', '42487369604123416352269861877665591482411615626788429745927015472511519216127,25294232410104585031042768427931216588096543230548057849614328629410995775276,4858064310490583090182920749490673370064354574537157073411888075460282103000,45193395449885706600096508744494563246749916505318009049280704109610236154940,18403444935017055418651866829605890471473756579696763770680460328838969647993,62679358251970494842462821164882016864696918706654247371362743677508365914129890408518396845432436505653354082533972429830641983567284617132876144234619782', '2025-03-28 21:56:47', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (48, 10, 48, 7, 'T', '2', '52745647451430594244698111779404151641016421648334876989289694660746748083063,2515011308427246647337870193462504084665484072233862400490985582149876269753,35424986967634459366662803045247880046056228813877670137301166149225006898577,39982673180136933662288894647803859685989884052551148382387918092342767179045,47570740913448833384353089491881976017121449866262794915278125052267823629689,57253763277021419239453800756046710641658014942204801173910703922098262771264278244945118961891090790408001421880563827498839629817381547416068698093466086', '2025-03-28 21:56:53', '否', '验签成功');
INSERT INTO `exam_answer` VALUES (49, 10, 49, 7, 'F', '2', '32907677886356862102405881805694757382871533564847644809872524357454858772447,2669184400408609827120855613357823587351884565774723135068419729956609836814,39299357656640159651322364261937141187441385476096718499430392024654023637826,39010202866030842837771193942031623429329535929083432254679052867345283689768,9648514936979327847614096204171551301582148653713799259319323874818735733905,55735107267323003805692421656738075246253044078814129703936968950690330150693511979704180682067578312064884212252317586926431564373704725450598649637402688', '2025-03-28 21:56:57', '否', '验签成功');

-- ----------------------------
-- Table structure for exam_manage
-- ----------------------------
DROP TABLE IF EXISTS `exam_manage`;
CREATE TABLE `exam_manage`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '该次考试介绍',
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程名称',
  `exam_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试日期',
  `total_time` int(255) NULL DEFAULT NULL COMMENT '持续时长',
  `total_score` int(255) NULL DEFAULT NULL COMMENT '总分',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考试类型',
  `tips` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '考生须知',
  `public_keys` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '环公钥',
  `is_marked` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否阅卷完成',
  `begin_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '结束时间',
  `is_submit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否发布',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '考试试卷管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exam_manage
-- ----------------------------
INSERT INTO `exam_manage` VALUES (10, '<h1>数据结构期中考试介绍</h1><h2>一、考试目的</h2><p>本次数据结构期中考试旨在全面考查学生对数据结构基础概念、经典算法的理解，以及运用所学知识解决实际问题的能力。通过考试反馈，教师能精准把握学生的学习状况，适时调整教学策略，为后续教学的高效开展提供有力支撑。</p><h2>二、考试范围</h2><ol><li><strong>数据结构基础</strong>：线性表（顺序表、链表）、栈、队列、树（二叉树、二叉搜索树等）、图（有向图、无向图）的定义、特点和存储结构。</li><li><strong>算法实现与分析</strong>：常见数据结构上的基本操作算法，如插入、删除、查找等；算法时间复杂度和空间复杂度的分析方法。</li><li><strong>综合应用</strong>：运用数据结构和算法解决实际问题，如利用栈实现表达式求值，使用图的算法解决路径规划问题。</li></ol><h2>三、考试形式与题型</h2><ol><li><strong>考试形式</strong>：闭卷笔试，考试时长为 120 分钟。</li><li><strong>题型分布</strong><ul><li><strong>选择题</strong>：主要考查学生对基本概念、原理和算法的理解。例如，考查某种数据结构的特点、算法的时间复杂度等。</li><li><strong>填空题</strong>：重点考查学生对关键知识点的记忆和应用。如填写某种数据结构的操作步骤、算法的关键参数等。</li><li><strong>判断题</strong>：检验学生对概念和性质的准确判断能力。例如，判断关于数据结构特性描述的正误。</li><li><strong>简答题</strong>：要求学生对一些重要概念、算法或原理进行详细解释和分析。如简述某种排序算法的原理和优缺点。</li><li><strong>编程题</strong>：要求学生根据给定问题，运用所学的数据结构和算法知识编写完整的程序。例如，实现一个栈的基本操作。</li></ul></li></ol><h2>四、成绩评定与反馈</h2><ol><li><strong>成绩评定</strong>：考试成绩满分为 100 分，根据各题型的得分总和确定最终成绩。</li><li><strong>成绩反馈</strong>：考试结束后两周内公布成绩。教师会对试卷进行详细讲评，分析学生的常见错误和知识薄弱点，为后续教学提供参考。学生可在课后向教师咨询考试相关问题，以加深对知识点的理解。</li></ol><p><br/></p>', '6', '2025-04-18 22:45:15', 1200, 75, '单元测试', '<h1>数据结构期中考试考生须知</h1><ol><li><strong>报考与规则知晓</strong><ul><li>考生需认真研读《考试报考说明》，全面理解并认同其中所有条款。</li></ul></li><li><strong>证件要求与携带规定</strong><ul><li>考生凭本人有效证件完成考试报名，务必仔细填写并核对报考相关信息，每位考生在本次期中考试中仅可报名一次。</li><li><strong>考试当日证件查验</strong>：若考生未携带报名时登记的证件，或该证件不符合报考规定，又或者所持证件与准考证上的信息不一致，将被禁止进入考场，且考试费用不予退还。</li><li><strong>证件类型细则</strong><ul><li><strong>中国大陆考生</strong>：必须携带居民身份证原件。依据《中华人民共和国身份证法》，任何年龄段的居民均可向户籍所在地派出所申领身份证。尚未申领身份证的青少年考生应提前办理，防止影响考试。</li><li><strong>中国香港特别行政区、中国澳门特别行政区考生</strong>：需持有效的港澳居民身份证、港澳居民居住证、港澳来往内地通行证原件。</li><li><strong>中国台湾地区考生</strong>：须持有效的台湾居民居住证、台湾居民来往大陆通行证原件。</li><li><strong>外籍考生</strong>：要携带有效的外国人永久居留身份证、护照原件。护照上应清晰显示考生姓名、照片和签字。若外籍考生所持外国护照上没有中国签证（中国政府规定免签国家除外），则视为无效证件。</li></ul></li><li><strong>特别提醒</strong>：证件上的所有个人信息必须与报名时填写的内容完全一致，同时要保证考试时证件处于有效期内。</li></ul></li><li><strong>个人信息填写与修改规定</strong><ul><li>考生在报名注册时，所填写的个人信息资料必须真实准确。在提交报名信息并完成缴费后，可在规定时间内进行一次个人信息及照片勘误，具体时间以 “公告通知” 中的报名工作安排为准。勘误截止后，即表明考生确认并承诺所提交内容的真实性、准确性和合法性，之后不得再要求修改证件类型、证件号码、中文姓名、性别和出生日期。因考生个人信息填写错误而导致无法正常参加考试的，责任由考生自行承担。</li></ul></li><li><strong>考试用品准备</strong><ul><li>考生需携带黑色中性笔、2B 铅笔、橡皮等必要的答题文具。严禁携带任何电子设备（如手机、智能手表、计算器等）、书籍、笔记等进入考场。若发现违规携带，将按考试违纪处理。</li></ul></li><li><strong>考场纪律与要求</strong><ul><li>考生须在开考前 15 分钟到达考场，凭准考证和有效证件进入指定座位就座。开考 15 分钟后，禁止迟到考生进入考场。</li><li>考试期间，考生要严格遵守考场纪律，保持安静，不得交头接耳、左顾右盼、抄袭或协助他人抄袭。如有违反，将按照考试违纪处理办法进行严肃处理。</li><li>考生不得提前交卷离开考场，考试结束铃声响起后，应立即停止答题，将试卷和答题卡放在桌面上，待监考人员收齐后，有序离开考场。</li></ul></li><li><strong>成绩与复查</strong><ul><li>考试结束后，将按照规定流程进行阅卷和成绩公布。考生可在指定时间和平台查询成绩。</li><li>若考生对成绩有异议，可在成绩公布后的规定时间内，按照相关流程申请成绩复查。复查仅限于核对卷面分数的统计是否有误，不涉及评分标准的重新评判。</li></ul></li></ol><p><br/></p>', '马文:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJrBml1AxU8E7bJKcuz/qQZ3FdK0rsC2OKLNzcbYEscvvwS6WY4M7RVVipRFIUsbSGWEDvAu6dxKccOr3uhWnDw=, 刘诗慧:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABC3HIf3zrM4G3jpV7HmHmICLiBr8aiXmXu7BC9c3Li6p6JlhiXmsDVWEZUlQJW23tqnpX+bBpFGYpBltfbqQRrE=, 李安:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABD+AiFSB1lxxgvt3GEMi0kXXAakhj5utgThLcVBOhn1yb8u1ZnU4oRUAX9y4JL1Y2eSlzO590gvlpqIpiSP7m5M=, 王凯:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJzSWJ6blwU2vZTbe3XCwEwA6eH8bS+waCo94NPHv++9H1f8jbZT6t0bym3q7lAvocaMPyTkytavNDAg260PPA0=', '是', '2025-04-13 22:20:22', '2025-04-14 22:20:22', '是');
INSERT INTO `exam_manage` VALUES (11, '<p>12</p>', '4', '2025-04-09 00:00:00', 5, 0, '122121', '<p>11</p>', '马文:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJrBml1AxU8E7bJKcuz/qQZ3FdK0rsC2OKLNzcbYEscvvwS6WY4M7RVVipRFIUsbSGWEDvAu6dxKccOr3uhWnDw=, 刘诗慧:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABC3HIf3zrM4G3jpV7HmHmICLiBr8aiXmXu7BC9c3Li6p6JlhiXmsDVWEZUlQJW23tqnpX+bBpFGYpBltfbqQRrE=, 李安:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABD+AiFSB1lxxgvt3GEMi0kXXAakhj5utgThLcVBOhn1yb8u1ZnU4oRUAX9y4JL1Y2eSlzO590gvlpqIpiSP7m5M=, 王凯:MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJzSWJ6blwU2vZTbe3XCwEwA6eH8bS+waCo94NPHv++9H1f8jbZT6t0bym3q7lAvocaMPyTkytavNDAg260PPA0=', '否', NULL, NULL, '否');

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试题id',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '试卷id',
  `question_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '问题类型',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '考试试题管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES (40, 10, 'multi', 10044);
INSERT INTO `exam_paper` VALUES (41, 10, 'multi', 10043);
INSERT INTO `exam_paper` VALUES (42, 10, 'multi', 10042);
INSERT INTO `exam_paper` VALUES (43, 10, 'multi', 10041);
INSERT INTO `exam_paper` VALUES (44, 10, 'multi', 10040);
INSERT INTO `exam_paper` VALUES (45, 10, 'judge', 10020);
INSERT INTO `exam_paper` VALUES (46, 10, 'judge', 10019);
INSERT INTO `exam_paper` VALUES (47, 10, 'judge', 10018);
INSERT INTO `exam_paper` VALUES (48, 10, 'judge', 10017);
INSERT INTO `exam_paper` VALUES (49, 10, 'judge', 10016);
INSERT INTO `exam_paper` VALUES (50, 10, 'fill', 10035);
INSERT INTO `exam_paper` VALUES (51, 10, 'fill', 10034);
INSERT INTO `exam_paper` VALUES (52, 10, 'fill', 10033);
INSERT INTO `exam_paper` VALUES (53, 10, 'fill', 10032);
INSERT INTO `exam_paper` VALUES (54, 10, 'fill', 10031);
INSERT INTO `exam_paper` VALUES (55, 10, 'shortAns', 7);
INSERT INTO `exam_paper` VALUES (56, 10, 'shortAns', 6);
INSERT INTO `exam_paper` VALUES (57, 10, 'shortAns', 5);
INSERT INTO `exam_paper` VALUES (58, 10, 'shortAns', 4);
INSERT INTO `exam_paper` VALUES (59, 10, 'shortAns', 3);
INSERT INTO `exam_paper` VALUES (60, 10, 'code', 10009);
INSERT INTO `exam_paper` VALUES (61, 10, 'code', 10008);
INSERT INTO `exam_paper` VALUES (62, 10, 'code', 10007);
INSERT INTO `exam_paper` VALUES (63, 10, 'code', 10006);
INSERT INTO `exam_paper` VALUES (64, 10, 'code', 10005);
INSERT INTO `exam_paper` VALUES (65, 10, 'code', 10004);

-- ----------------------------
-- Table structure for exam_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_score`;
CREATE TABLE `exam_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `manager_id` int(11) NULL DEFAULT NULL COMMENT '试卷id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '分数',
  `score_num` decimal(10, 2) GENERATED ALWAYS AS (cast(`score` as decimal(10,2))) VIRTUAL NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '考试成绩统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_score
-- ----------------------------
INSERT INTO `exam_score` VALUES (3, 10, 8, '0', DEFAULT);
INSERT INTO `exam_score` VALUES (4, 10, 6, '23', DEFAULT);
INSERT INTO `exam_score` VALUES (5, 10, 7, '12', DEFAULT);

-- ----------------------------
-- Table structure for exam_student
-- ----------------------------
DROP TABLE IF EXISTS `exam_student`;
CREATE TABLE `exam_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '考试id',
  `is_submit` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '提交标识',
  `face_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '人脸认证照片',
  `face_submit` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '人脸认证是否成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '学生答题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_student
-- ----------------------------
INSERT INTO `exam_student` VALUES (5, 8, 10, '未提交', NULL, NULL);
INSERT INTO `exam_student` VALUES (6, 6, 10, '未提交', 'http://localhost:9998/files/testFace/3bb65123-f379-445c-af5b-cebae962ef2b.jpg', '验证成功');
INSERT INTO `exam_student` VALUES (7, 7, 10, '未提交', 'http://localhost:9998/files/testFace/8f95f7be-3ccb-4706-b8aa-66c76eb628d3.jpg', '验证成功');

-- ----------------------------
-- Table structure for exam_teacher_grading
-- ----------------------------
DROP TABLE IF EXISTS `exam_teacher_grading`;
CREATE TABLE `exam_teacher_grading`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '批阅id',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '试卷id',
  `paper_id` int(11) NULL DEFAULT NULL COMMENT '批阅试题id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '分配时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '考试阅卷管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_teacher_grading
-- ----------------------------
INSERT INTO `exam_teacher_grading` VALUES (79, 10, 40, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (80, 10, 41, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (81, 10, 42, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (82, 10, 43, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (83, 10, 44, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (84, 10, 45, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (85, 10, 46, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (86, 10, 47, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (87, 10, 48, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (88, 10, 49, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (89, 10, 50, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (90, 10, 51, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (91, 10, 52, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (92, 10, 53, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (93, 10, 54, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (94, 10, 55, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (95, 10, 56, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (96, 10, 57, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (97, 10, 58, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (98, 10, 59, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (99, 10, 60, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (100, 10, 61, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (101, 10, 62, 5, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (102, 10, 63, 6, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (103, 10, 64, 4, '2025-03-28 22:20:26');
INSERT INTO `exam_teacher_grading` VALUES (104, 10, 65, 5, '2025-03-28 22:20:26');

-- ----------------------------
-- Table structure for markdown
-- ----------------------------
DROP TABLE IF EXISTS `markdown`;
CREATE TABLE `markdown`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'mdid',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建者角色',
  `userId` int(11) NULL DEFAULT NULL COMMENT '创建者id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '题目',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文档地址',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'markdown文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of markdown
-- ----------------------------
INSERT INTO `markdown` VALUES (1, 'student', 6, '我的日志', '/files/markdown/1744532638951-85640729-02184954.md', '2025-04-13 16:23:58');

-- ----------------------------
-- Table structure for question_apple_in_course
-- ----------------------------
DROP TABLE IF EXISTS `question_apple_in_course`;
CREATE TABLE `question_apple_in_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `from_course_id` int(11) NULL DEFAULT NULL COMMENT '问题来源',
  `apple_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '申请书',
  `apple_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '申请时间',
  `to_course_id` int(11) NULL DEFAULT NULL COMMENT '问题去向',
  `reply_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '回复建议',
  `reply_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '回复时间',
  `is_agree` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否同意',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '题库的申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_apple_in_course
-- ----------------------------

-- ----------------------------
-- Table structure for question_code
-- ----------------------------
DROP TABLE IF EXISTS `question_code`;
CREATE TABLE `question_code`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `course_id` int(255) NULL DEFAULT NULL COMMENT '考试科目ID',
  `question` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '试题内容',
  `answer` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目解析',
  `score` int(255) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `section` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10010 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '编程题题库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_code
-- ----------------------------
INSERT INTO `question_code` VALUES (10004, 6, '<p>设计一个函数，在单链表的指定位置插入一个新节点。单链表的节点包含一个整数数据和一个指向下一个节点的指针。</p><p><p><path></path></p><p><path></path></p><p><path></path></p><p><path></path></p><p><br/></p></p><p><path></path></p>', '#include <stdio.h>\r\n#include <stdlib.h>\r\n\r\n// 定义单链表节点结构\r\ntypedef struct Node {\r\n    int data;\r\n    struct Node* next;\r\n} Node;\r\n\r\n// 在指定位置插入新节点\r\nNode* insertNode(Node* head, int position, int value) {\r\n    Node* newNode = (Node*)malloc(sizeof(Node));\r\n    newNode->data = value;\r\n\r\n    if (position == 0) {\r\n        newNode->next = head;\r\n        return newNode;\r\n    }\r\n\r\n    Node* current = head;\r\n    for (int i = 0; i < position - 1 && current != NULL; i++) {\r\n        current = current->next;\r\n    }\r\n\r\n    if (current == NULL) {\r\n        free(newNode);\r\n        return head;\r\n    }\r\n\r\n    newNode->next = current->next;\r\n    current->next = newNode;\r\n    return head;\r\n}\r\n\r\n// 打印链表\r\nvoid printList(Node* head) {\r\n    Node* current = head;\r\n    while (current != NULL) {\r\n        printf(\"%d \", current->data);\r\n        current = current->next;\r\n    }\r\n    printf(\"\\n\");\r\n}\r\n\r\nint main() {\r\n    Node* head = NULL;\r\n    head = insertNode(head, 0, 1);\r\n    head = insertNode(head, 1, 2);\r\n    head = insertNode(head, 1, 3);\r\n    printList(head);\r\n    return 0;\r\n}', '<ol><li><strong>节点结构定义</strong>：定义了一个名为&nbsp;<code>Node</code>&nbsp;的结构体，包含一个整数&nbsp;<code>data</code>&nbsp;和一个指向下一个节点的指针&nbsp;<code>next</code>。</li><li><strong>插入函数</strong>：<code>insertNode</code>&nbsp;函数用于在指定位置插入新节点。若位置为 0，新节点成为头节点；否则，遍历链表找到指定位置的前一个节点，然后插入新节点。</li><li><strong>打印函数</strong>：<code>printList</code>&nbsp;函数用于遍历链表并打印每个节点的数据。</li><li><strong>主函数</strong>：创建一个空链表，调用&nbsp;<code>insertNode</code>&nbsp;函数插入节点，最后调用&nbsp;<code>printList</code>&nbsp;函数打印链表。</li></ol>', 5, '4', '链表');
INSERT INTO `question_code` VALUES (10005, 6, '<p>实现一个栈的基本操作，包括入栈、出栈和判断栈是否为空。栈使用数组来实现。<br/></p>', '#include <stdio.h>\r\n#include <stdlib.h>\r\n\r\n#define MAX_SIZE 100\r\n\r\n// 定义栈结构\r\ntypedef struct Stack {\r\n    int data[MAX_SIZE];\r\n    int top;\r\n} Stack;\r\n\r\n// 初始化栈\r\nvoid initStack(Stack* stack) {\r\n    stack->top = -1;\r\n}\r\n\r\n// 判断栈是否为空\r\nint isEmpty(Stack* stack) {\r\n    return stack->top == -1;\r\n}\r\n\r\n// 入栈操作\r\nvoid push(Stack* stack, int value) {\r\n    if (stack->top == MAX_SIZE - 1) {\r\n        printf(\"Stack overflow\\n\");\r\n        return;\r\n    }\r\n    stack->data[++stack->top] = value;\r\n}\r\n\r\n// 出栈操作\r\nint pop(Stack* stack) {\r\n    if (isEmpty(stack)) {\r\n        printf(\"Stack underflow\\n\");\r\n        return -1;\r\n    }\r\n    return stack->data[stack->top--];\r\n}\r\n\r\nint main() {\r\n    Stack stack;\r\n    initStack(&stack);\r\n    push(&stack, 1);\r\n    push(&stack, 2);\r\n    printf(\"Popped: %d\\n\", pop(&stack));\r\n    printf(\"Popped: %d\\n\", pop(&stack));\r\n    return 0;\r\n}', '<ol><li><strong>栈结构定义</strong>：定义了一个名为&nbsp;<code>Stack</code>&nbsp;的结构体，包含一个数组&nbsp;<code>data</code>&nbsp;用于存储栈元素，以及一个整数&nbsp;<code>top</code>&nbsp;表示栈顶位置。</li><li><strong>初始化函数</strong>：<code>initStack</code>&nbsp;函数将栈顶位置初始化为 -1，表示栈为空。</li><li><strong>判断栈空函数</strong>：<code>isEmpty</code>&nbsp;函数通过检查栈顶位置是否为 -1 来判断栈是否为空。</li><li><strong>入栈函数</strong>：<code>push</code>&nbsp;函数将元素压入栈中，若栈已满则输出错误信息。</li><li><strong>出栈函数</strong>：<code>pop</code>&nbsp;函数从栈中弹出元素，若栈为空则输出错误信息。</li><li><strong>主函数</strong>：创建一个栈，调用&nbsp;<code>push</code>&nbsp;函数入栈元素，再调用&nbsp;<code>pop</code>&nbsp;函数出栈元素并打印。</li></ol>', 5, '3', '栈与队列');
INSERT INTO `question_code` VALUES (10006, 6, '<p>实现二叉树的前序遍历，二叉树的节点包含一个整数数据和左右子节点指针。<br/></p>', '#include <stdio.h>\r\n#include <stdlib.h>\r\n\r\n// 定义二叉树节点结构\r\ntypedef struct TreeNode {\r\n    int data;\r\n    struct TreeNode* left;\r\n    struct TreeNode* right;\r\n} TreeNode;\r\n\r\n// 创建新节点\r\nTreeNode* createNode(int value) {\r\n    TreeNode* newNode = (TreeNode*)malloc(sizeof(TreeNode));\r\n    newNode->data = value;\r\n    newNode->left = NULL;\r\n    newNode->right = NULL;\r\n    return newNode;\r\n}\r\n\r\n// 前序遍历\r\nvoid preOrderTraversal(TreeNode* root) {\r\n    if (root == NULL) return;\r\n    printf(\"%d \", root->data);\r\n    preOrderTraversal(root->left);\r\n    preOrderTraversal(root->right);\r\n}\r\n\r\nint main() {\r\n    TreeNode* root = createNode(1);\r\n    root->left = createNode(2);\r\n    root->right = createNode(3);\r\n    root->left->left = createNode(4);\r\n    root->left->right = createNode(5);\r\n\r\n    preOrderTraversal(root);\r\n    printf(\"\\n\");\r\n    return 0;\r\n}', '<ol><li><strong>节点结构定义</strong>：定义了一个名为&nbsp;<code>TreeNode</code>&nbsp;的结构体，包含一个整数&nbsp;<code>data</code>&nbsp;和左右子节点指针&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>。</li><li><strong>创建节点函数</strong>：<code>createNode</code>&nbsp;函数用于创建一个新的二叉树节点，并初始化其数据和左右子节点指针。</li><li><strong>前序遍历函数</strong>：<code>preOrderTraversal</code>&nbsp;函数实现了二叉树的前序遍历，先访问根节点，再递归遍历左子树和右子树。</li><li><strong>主函数</strong>：创建一个二叉树，调用&nbsp;<code>preOrderTraversal</code>&nbsp;函数进行前序遍历并打印结果。</li></ol>', 5, '5', '二叉树');
INSERT INTO `question_code` VALUES (10007, 6, '<p>给定一个顺序表，编写一个函数将顺序表中的元素逆置。</p>', '#include <stdio.h>\r\n#define MAX_SIZE 100\r\n\r\ntypedef struct {\r\n    int data[MAX_SIZE];\r\n    int length;\r\n} SeqList;\r\n\r\n// 逆置顺序表元素\r\nvoid reverseList(SeqList *list) {\r\n    int i, j, temp;\r\n    for (i = 0, j = list->length - 1; i < j; i++, j--) {\r\n        temp = list->data[i];\r\n        list->data[i] = list->data[j];\r\n        list->data[j] = temp;\r\n    }\r\n}\r\n\r\n// 打印顺序表\r\nvoid printList(SeqList list) {\r\n    for (int i = 0; i < list.length; i++) {\r\n        printf(\"%d \", list.data[i]);\r\n    }\r\n    printf(\"\\n\");\r\n}\r\n\r\nint main() {\r\n    SeqList list;\r\n    list.length = 5;\r\n    for (int i = 0; i < list.length; i++) {\r\n        list.data[i] = i + 1;\r\n    }\r\n    printf(\"逆置前: \");\r\n    printList(list);\r\n    reverseList(&list);\r\n    printf(\"逆置后: \");\r\n    printList(list);\r\n    return 0;\r\n}', '<ul><li>定义顺序表结构体&nbsp;<code>SeqList</code>，包含一个数组&nbsp;<code>data</code>&nbsp;用于存储元素，以及一个整数&nbsp;<code>length</code>&nbsp;表示顺序表的长度。</li><li><code>reverseList</code>&nbsp;函数使用两个指针&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;分别指向顺序表的首尾，交换它们所指元素的值，然后向中间移动指针，直到&nbsp;<code>i &gt;= j</code>。</li><li><code>printList</code>&nbsp;函数用于遍历顺序表并打印元素。</li><li>在&nbsp;<code>main</code>&nbsp;函数中，初始化顺序表，调用&nbsp;<code>reverseList</code>&nbsp;函数逆置元素，并打印逆置前后的顺序表。</li></ul>', 5, '1', '线性表');
INSERT INTO `question_code` VALUES (10008, 6, '<p>使用栈将一个十进制整数转换为二进制数。</p>', '#include <stdio.h>\r\n#include <stdlib.h>\r\n\r\n#define MAX_SIZE 100\r\n\r\ntypedef struct {\r\n    int data[MAX_SIZE];\r\n    int top;\r\n} Stack;\r\n\r\n// 初始化栈\r\nvoid initStack(Stack *stack) {\r\n    stack->top = -1;\r\n}\r\n\r\n// 判断栈是否为空\r\nint isEmpty(Stack *stack) {\r\n    return stack->top == -1;\r\n}\r\n\r\n// 入栈\r\nvoid push(Stack *stack, int value) {\r\n    if (stack->top == MAX_SIZE - 1) {\r\n        printf(\"Stack overflow\\n\");\r\n        return;\r\n    }\r\n    stack->data[++stack->top] = value;\r\n}\r\n\r\n// 出栈\r\nint pop(Stack *stack) {\r\n    if (isEmpty(stack)) {\r\n        printf(\"Stack underflow\\n\");\r\n        return -1;\r\n    }\r\n    return stack->data[stack->top--];\r\n}\r\n\r\n// 十进制转二进制\r\nvoid decimalToBinary(int num) {\r\n    Stack stack;\r\n    initStack(&stack);\r\n    while (num > 0) {\r\n        push(&stack, num % 2);\r\n        num /= 2;\r\n    }\r\n    while (!isEmpty(&stack)) {\r\n        printf(\"%d\", pop(&stack));\r\n    }\r\n    printf(\"\\n\");\r\n}\r\n\r\nint main() {\r\n    int num = 10;\r\n    printf(\"十进制 %d 转换为二进制: \", num);\r\n    decimalToBinary(num);\r\n    return 0;\r\n}', '<ul><li>定义栈结构体&nbsp;<code>Stack</code>，包含一个数组&nbsp;<code>data</code>&nbsp;用于存储元素，以及一个整数&nbsp;<code>top</code>&nbsp;表示栈顶位置。</li><li><code>initStack</code>&nbsp;函数将栈顶位置初始化为 -1，表示栈为空。</li><li><code>isEmpty</code>&nbsp;函数判断栈是否为空。</li><li><code>push</code>&nbsp;函数将元素入栈，若栈满则输出错误信息。</li><li><code>pop</code>&nbsp;函数将元素出栈，若栈空则输出错误信息。</li><li><code>decimalToBinary</code>&nbsp;函数通过不断取模和整除操作将十进制数转换为二进制数，并将每一位入栈，最后依次出栈打印。</li></ul>', 10, '4', '栈与队列');
INSERT INTO `question_code` VALUES (10009, 6, '<p>编写一个递归函数实现二叉树的中序遍历。</p>', '#include <stdio.h>\r\n#include <stdlib.h>\r\n\r\ntypedef struct TreeNode {\r\n    int data;\r\n    struct TreeNode *left;\r\n    struct TreeNode *right;\r\n} TreeNode;\r\n\r\n// 创建新节点\r\nTreeNode* createNode(int value) {\r\n    TreeNode *newNode = (TreeNode*)malloc(sizeof(TreeNode));\r\n    newNode->data = value;\r\n    newNode->left = NULL;\r\n    newNode->right = NULL;\r\n    return newNode;\r\n}\r\n\r\n// 中序遍历\r\nvoid inorderTraversal(TreeNode *root) {\r\n    if (root == NULL) return;\r\n    inorderTraversal(root->left);\r\n    printf(\"%d \", root->data);\r\n    inorderTraversal(root->right);\r\n}\r\n\r\nint main() {\r\n    TreeNode *root = createNode(1);\r\n    root->left = createNode(2);\r\n    root->right = createNode(3);\r\n    root->left->left = createNode(4);\r\n    root->left->right = createNode(5);\r\n\r\n    printf(\"中序遍历结果: \");\r\n    inorderTraversal(root);\r\n    printf(\"\\n\");\r\n    return 0;\r\n}', '<ul><li>定义二叉树节点结构体&nbsp;<code>TreeNode</code>，包含一个整数&nbsp;<code>data</code>&nbsp;以及左右子节点指针&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>。</li><li><code>createNode</code>&nbsp;函数用于创建一个新的二叉树节点。</li><li><code>inorderTraversal</code>&nbsp;函数是递归实现的中序遍历函数，先递归遍历左子树，然后访问根节点，最后递归遍历右子树。</li><li>在&nbsp;<code>main</code>&nbsp;函数中，构建一个二叉树，调用&nbsp;<code>inorderTraversal</code>&nbsp;函数进行中序遍历并打印结果。</li></ul>', 5, '2', '二叉树');

-- ----------------------------
-- Table structure for question_fill
-- ----------------------------
DROP TABLE IF EXISTS `question_fill`;
CREATE TABLE `question_fill`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `course_id` int(255) NULL DEFAULT NULL COMMENT '考试科目',
  `question` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '试题内容',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目解析',
  `score` int(255) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `section` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10036 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '填空题题库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_fill
-- ----------------------------
INSERT INTO `question_fill` VALUES (10031, 6, '<p>在一个长度为&nbsp;n&nbsp;的顺序表中，删除第&nbsp;i&nbsp;个元素（\\(1\\leq i\\leq n\\)）时，需要向前移动______个元素。</p>', 'n−i', '<p>在顺序表中删除第&nbsp;i&nbsp;个元素后，从第&nbsp;\\(i + 1\\)&nbsp;个元素开始，直到第&nbsp;n&nbsp;个元素都要向前移动一个位置，第&nbsp;\\(i + 1\\)&nbsp;个元素到第&nbsp;n&nbsp;个元素一共有&nbsp;\\(n - i\\)&nbsp;个元素，所以需要向前移动&nbsp;\\(n - i\\)&nbsp;个元素。</p>', 2, '1', '线性表');
INSERT INTO `question_fill` VALUES (10032, 6, '<p>栈的插入和删除操作在______进行，队列的插入操作在______进行，删除操作在______进行。</p>', '栈顶；队尾；队头', '<p>栈是一种后进先出（LIFO）的数据结构，所有的插入（入栈）和删除（出栈）操作都在栈顶进行。队列是一种先进先出（FIFO）的数据结构，新元素的插入在队尾进行，而删除元素则在队头进行。</p>', 2, '2', '栈和队列');
INSERT INTO `question_fill` VALUES (10033, 6, '<p>高度为&nbsp;h&nbsp;的满二叉树的节点数为______，叶子节点数为______。</p>', '\\(2^{h}-1\\)；\\(2^{h - 1}\\)', '<ul><li>对于满二叉树，第&nbsp;i&nbsp;层的节点数为&nbsp;\\(2^{i - 1}\\)（\\(i\\geq1\\)）。高度为&nbsp;h&nbsp;的满二叉树的节点总数是各层节点数之和，根据等比数列求和公式&nbsp;\\(S_n=\\frac{a_1(1 - q^n)}{1 - q}\\)（这里&nbsp;\\(a_1 = 1\\)，\\(q = 2\\)，\\(n = h\\)），可得节点总数为&nbsp;\\(2^{h}-1\\)。</li><li>满二叉树的叶子节点都在最后一层，最后一层是第&nbsp;h&nbsp;层，该层节点数为&nbsp;\\(2^{h - 1}\\)，所以叶子节点数为&nbsp;\\(2^{h - 1}\\)。</li></ul>', 2, '5', '树和二叉树');
INSERT INTO `question_fill` VALUES (10034, 6, '<p>在一个具有&nbsp;n&nbsp;个顶点和&nbsp;e&nbsp;条边的无向图的邻接矩阵中，零元素的个数为______。</p>', '(n^2)−2e', '<p>无向图的邻接矩阵是一个&nbsp;nxn&nbsp;的矩阵，矩阵元素总数为&nbsp;n^2。对于无向图，每条边会在邻接矩阵中对应两个非零元素（因为无向图的邻接矩阵是对称的），所以非零元素的个数为&nbsp;2e，那么零元素的个数就是&nbsp;n^2 - 2e。</p>', 2, '3', '图');
INSERT INTO `question_fill` VALUES (10035, 6, '<p>在哈希表中，处理冲突的方法主要有______和______两大类。</p>', '开放定址法；链地址法', '<p>开放定址法是指当发生冲突时，在哈希表中寻找下一个空的位置来存放冲突的元素，常见的有线性探测法、二次探测法等。链地址法是将所有哈希地址相同的元素都链接在同一个链表中，当发生冲突时，只需将元素插入到对应的链表中即可。这两种是哈希表处理冲突的主要方法。</p>', 2, '4', '查找');

-- ----------------------------
-- Table structure for question_judge
-- ----------------------------
DROP TABLE IF EXISTS `question_judge`;
CREATE TABLE `question_judge`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `course_id` int(255) NULL DEFAULT NULL COMMENT '考试科目',
  `question` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '试题内容',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目解析',
  `score` int(255) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `section` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10021 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '判断题题库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_judge
-- ----------------------------
INSERT INTO `question_judge` VALUES (10016, 6, '<p>顺序表的插入和删除操作效率一定比链表高。（ ）</p>', 'F', '<p>顺序表在进行插入和删除操作时，通常需要移动大量元素，时间复杂度为&nbsp;\\(O(n)\\)，尤其是在表中间或开头进行操作时。而链表在插入和删除节点时，只需要修改指针，时间复杂度为&nbsp;\\(O(1)\\)（前提是已经知道要操作的位置）。所以，顺序表的插入和删除操作效率不一定比链表高，在某些情况下链表的效率更高。</p>', 2, '3', '线性表');
INSERT INTO `question_judge` VALUES (10017, 6, '<p>栈和队列都是线性数据结构，它们的插入和删除操作都遵循特定的规则。（ ）</p>', 'T', '<p>栈和队列都属于线性数据结构。栈遵循后进先出（LIFO）原则，插入（入栈）和删除（出栈）操作都在栈顶进行；队列遵循先进先出（FIFO）原则，插入操作在队尾进行，删除操作在队头进行，它们的插入和删除操作都有明确的规则。</p>', 2, '2', '栈和队列');
INSERT INTO `question_judge` VALUES (10018, 6, '<p>二叉树中，度为 0 的节点数总是比度为 2 的节点数多 1。（ ）</p>', 'T', '<p>设二叉树中度为 0、1、2 的节点数分别为&nbsp;\\(n_0\\)、\\(n_1\\)、\\(n_2\\)，节点总数为&nbsp;n，则&nbsp;\\(n=n_0 + n_1 + n_2\\)。同时，二叉树中除根节点外，每个节点都有一个分支进入，分支总数为&nbsp;\\(n - 1\\)，而分支是由度为 1 和度为 2 的节点发出的，所以分支总数又可表示为&nbsp;\\(n_1+2n_2\\)，即&nbsp;\\(n - 1=n_1 + 2n_2\\)。将&nbsp;\\(n=n_0 + n_1 + n_2\\)&nbsp;代入&nbsp;\\(n - 1=n_1 + 2n_2\\)&nbsp;可得&nbsp;\\(n_0=n_2 + 1\\)，所以度为 0 的节点数总是比度为 2 的节点数多 1。</p>', 2, '4', '树和二叉树');
INSERT INTO `question_judge` VALUES (10019, 6, '<p>在有向图中，所有顶点的入度之和等于所有顶点的出度之和。（ ）</p>', 'T', '<p>在有向图中，每一条有向边都对应一个入度和一个出度。一条有向边从一个顶点出发指向另一个顶点，会使出发顶点的出度加 1，到达顶点的入度加 1。所以，所有顶点的入度之和等于所有顶点的出度之和，都等于有向图的边数。</p>', 2, '1', '图');
INSERT INTO `question_judge` VALUES (10020, 6, '<p>在二分查找中，要求被查找的表必须是有序的顺序表。（ ）</p>', 'T', '<p>二分查找的基本思想是将有序表中间位置的元素与查找元素进行比较，根据比较结果缩小查找范围，重复这个过程直到找到目标元素或确定目标元素不存在。如果表是无序的，就无法根据中间元素与目标元素的大小关系来缩小查找范围。同时，二分查找需要通过下标快速定位元素，所以要求表是顺序表，这样才能实现随机访问。因此，在二分查找中，被查找的表必须是有序的顺序表。</p>', 2, '5', '查找');

-- ----------------------------
-- Table structure for question_multi
-- ----------------------------
DROP TABLE IF EXISTS `question_multi`;
CREATE TABLE `question_multi`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '试题编号',
  `course_id` int(255) NULL DEFAULT NULL COMMENT '考试科目ID',
  `question_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '单选/多选',
  `question` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '问题题目',
  `answerA` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项A',
  `answerB` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项B',
  `answerC` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项C',
  `answerD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项D',
  `rightAnswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目解析',
  `score` int(255) NULL DEFAULT 2 COMMENT '分数',
  `section` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属章节',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  PRIMARY KEY (`id`, `question_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10045 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '选择题题库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_multi
-- ----------------------------
INSERT INTO `question_multi` VALUES (10040, 6, '单选', '<p>在一个长度为&nbsp;n&nbsp;的顺序表中，在第&nbsp;i&nbsp;个元素(1&lt;=i&lt;=n + 1）之前插入一个新元素时，需向后移动（ ）个元素。</p>', 'n−i', 'n−i+1', 'n−i−1', 'i', 'B', '<p>在顺序表中第&nbsp;i&nbsp;个元素之前插入新元素，从第&nbsp;i&nbsp;个元素开始，直到第&nbsp;n&nbsp;个元素都要向后移动一个位置。第&nbsp;i&nbsp;个元素到第&nbsp;n&nbsp;个元素一共有&nbsp;\\(n - i+ 1\\)&nbsp;个元素，所以需要向后移动&nbsp;(n - i + 1)&nbsp;个元素。</p>', 2, '线性表', '1');
INSERT INTO `question_multi` VALUES (10041, 6, '单选', '<p>一个栈的输入序列为&nbsp;(1,2,3,……,n)，若输出序列的第一个元素是&nbsp;n，则输出序列的第&nbsp;i&nbsp;个元素是（ ）。</p>', '不确定', 'n−i+1', 'i', 'n−i', 'B', '<p>因为栈是后进先出的结构，输入序列为&nbsp;(1,2,3,……,n)，输出序列第一个元素是&nbsp;n，说明所有元素都已入栈。那么输出序列就是&nbsp;(n,n - 1,n - 2,……,1)，所以第&nbsp;i&nbsp;个元素是&nbsp;n-(i - 1)=n - i + 1。</p>', 2, '栈和队列', '2');
INSERT INTO `question_multi` VALUES (10042, 6, '单选', '<p>若某二叉树的前序遍历序列为&nbsp;ABCDE，中序遍历序列为&nbsp;CBADE，则该二叉树的后序遍历序列为（ ）。</p>', 'CBEDA', 'CBDEA', 'CBADE', 'EDCBA', 'A', '<p>前序遍历的顺序是根 - 左 - 右，中序遍历的顺序是左 - 根 - 右。由前序遍历序列&nbsp;ABCDE&nbsp;可知&nbsp;A&nbsp;是根节点；在中序遍历序列&nbsp;CBADE&nbsp;中，C&nbsp;和&nbsp;B&nbsp;在&nbsp;A&nbsp;左边，D&nbsp;和&nbsp;E&nbsp;在&nbsp;A&nbsp;右边，所以左子树的中序遍历序列是&nbsp;CB，右子树的中序遍历序列是&nbsp;DE。根据前序遍历中&nbsp;B&nbsp;在&nbsp;C&nbsp;前，可知&nbsp;B&nbsp;是左子树的根，C&nbsp;是&nbsp;B&nbsp;的左孩子；同理可得右子树结构。构建出二叉树后，后序遍历（左 - 右 - 根）的结果就是&nbsp;C B E D A。</p>', 2, '树和二叉树', '3');
INSERT INTO `question_multi` VALUES (10043, 6, '单选', '<p>在一个有向图中，所有顶点的入度之和等于所有顶点的出度之和的（ ）倍。</p>', '0.5', '1', '2', '4', 'B', '<p>在有向图中，每一条有向边都对应一个入度和一个出度。一条有向边从一个顶点出发指向另一个顶点，会使出发顶点的出度加 1，到达顶点的入度加 1。所以，所有顶点的入度之和等于所有顶点的出度之和，倍数为 1。</p>', 2, '图', '4');
INSERT INTO `question_multi` VALUES (10044, 6, '单选', '<p>对长度为&nbsp;n&nbsp;的有序表进行二分查找，其时间复杂度为（ ）</p>', 'O(n)', 'O(log 2n)', 'O(n^2)', 'O(nlog 2n)', 'B', '<p>二分查找每次将查找区间缩小一半，设查找区间长度为&nbsp;n，经过&nbsp;k&nbsp;次比较后，查找区间长度变为 1，即&nbsp;(n2^k = 1)，可得&nbsp;(k=log_2n)，所以二分查找的时间复杂度是&nbsp;(O(log_2n)。</p>', 2, '查找', '5');

-- ----------------------------
-- Table structure for question_shortans
-- ----------------------------
DROP TABLE IF EXISTS `question_shortans`;
CREATE TABLE `question_shortans`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '简答题主键',
  `course_id` int(255) NULL DEFAULT NULL COMMENT '考试科目',
  `question` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '试题内容',
  `answer` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '题目解析',
  `score` int(255) NULL DEFAULT 2 COMMENT '分数',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '难度等级',
  `section` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '简答题\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_shortans
-- ----------------------------
INSERT INTO `question_shortans` VALUES (3, 6, '<p>请简要阐述线性表顺序存储结构和链式存储结构各自的优缺点。</p>', '<ul><li><strong>顺序存储结构</strong><ul><li><strong>优点</strong>：<ul><li>随机访问效率高，可通过下标直接访问任意元素，时间复杂度为&nbsp;\\(O(1)\\)。</li><li>存储密度大，不需要额外的指针域，空间利用率高。</li></ul></li><li><strong>缺点</strong>：<ul><li>插入和删除操作效率低，平均需要移动约一半的元素，时间复杂度为&nbsp;\\(O(n)\\)。</li><li>预先分配存储空间，可能导致空间浪费或不足。</li></ul></li></ul></li><li><strong>链式存储结构</strong><ul><li><strong>优点</strong>：<ul><li>插入和删除操作效率高，只需修改指针，时间复杂度为&nbsp;\\(O(1)\\)（前提是已知操作位置）。</li><li>动态分配存储空间，不会造成空间浪费或不足。</li></ul></li><li><strong>缺点</strong>：<ul><li>随机访问效率低，需要从头节点开始遍历，时间复杂度为&nbsp;\\(O(n)\\)。</li><li>存储密度小，每个节点需要额外的指针域，空间开销大。</li></ul></li></ul></li></ul>', '<p>顺序存储结构利用数组实现，数组的特点决定了可以通过下标直接计算元素地址，所以随机访问快，但插入和删除时移动元素操作多。而链式存储结构通过节点和指针连接，插入和删除只需修改指针，但查找元素只能依次遍历。</p>', 5, '1', '线性表');
INSERT INTO `question_shortans` VALUES (4, 6, '<p>简述栈和队列的特点，并各举两个应用场景。</p>', '<ul><li><strong>栈的特点</strong>：后进先出（LIFO），即最后进入栈的元素最先出栈。<ul><li><strong>应用场景</strong>：<ul><li>函数调用：在程序执行过程中，函数调用时的参数传递、局部变量存储等信息会压入栈中，函数返回时再从栈中弹出。</li><li>表达式求值：如后缀表达式求值，利用栈来处理运算符和操作数。</li></ul></li></ul></li><li><strong>队列的特点</strong>：先进先出（FIFO），即最先进入队列的元素最先出队列。<ul><li><strong>应用场景</strong>：<ul><li>操作系统中的作业调度：多个作业按到达顺序进入队列，依次进行处理。</li><li>网络通信中的数据缓冲：数据包按到达顺序进入队列，依次发送。</li></ul></li></ul></li></ul>', '<p>栈的后进先出特点使其在需要逆序处理数据的场景中很有用，如函数调用的嵌套和表达式求值。队列的先进先出特点适合处理按顺序到达的数据，如作业调度和网络数据传输。</p>', 5, '2', '栈和队列');
INSERT INTO `question_shortans` VALUES (5, 6, '<p>列举二叉树的三种主要遍历方式，并说明它们的用途。</p>', '<ul><li><strong>前序遍历</strong>：先访问根节点，再递归遍历左子树，最后递归遍历右子树。<ul><li><strong>用途</strong>：常用于创建二叉树的副本、表达式树的求值（前缀表达式）等。</li></ul></li><li><strong>中序遍历</strong>：先递归遍历左子树，再访问根节点，最后递归遍历右子树。<ul><li><strong>用途</strong>：对于二叉排序树，中序遍历可以得到有序序列。</li></ul></li><li><strong>后序遍历</strong>：先递归遍历左子树，再递归遍历右子树，最后访问根节点。<ul><li><strong>用途</strong>：常用于释放二叉树的节点、计算表达式树的值（后缀表达式）等。</li></ul></li></ul>', '<p>不同的遍历方式按照不同的顺序访问二叉树的节点，从而满足不同的应用需求。前序遍历可以先处理根节点信息；中序遍历在二叉排序树中能体现其有序性；后序遍历在需要先处理子树信息再处理根节点信息的场景中很有用。</p>', 5, '3', '树和二叉树');
INSERT INTO `question_shortans` VALUES (6, 6, '<p>简述图的邻接矩阵和邻接表两种存储结构，并说明它们各自的适用场景。</p>', '<ul><li><strong>邻接矩阵</strong>：用一个二维数组来表示图中顶点之间的邻接关系。如果图中有&nbsp;n&nbsp;个顶点，则邻接矩阵是一个&nbsp;\\(n\\times n\\)&nbsp;的矩阵。<ul><li><strong>适用场景</strong>：适用于稠密图（边数接近顶点数的平方），因为可以方便地判断任意两个顶点之间是否有边相连，时间复杂度为&nbsp;\\(O(1)\\)。</li></ul></li><li><strong>邻接表</strong>：为图中每个顶点建立一个单链表，链表中存储与该顶点相邻接的顶点信息。<ul><li><strong>适用场景</strong>：适用于稀疏图（边数远小于顶点数的平方），可以节省存储空间，因为只存储实际存在的边。</li></ul></li></ul>', '<p>邻接矩阵通过二维数组存储所有顶点间的关系，对于稠密图能高效判断边的存在，但空间开销大；邻接表只存储实际的边信息，对于稀疏图空间利用率高，但判断两个顶点间是否有边需要遍历链表。</p>', 5, '4', '图');
INSERT INTO `question_shortans` VALUES (7, 6, '<p>简要说明哈希表处理冲突的开放定址法和链地址法的原理及优缺点。</p>', '<ul><li><strong>开放定址法</strong><ul><li><strong>原理</strong>：当发生冲突时，在哈希表中寻找下一个空的位置来存放冲突的元素。常见的有线性探测法、二次探测法等。</li><li><strong>优点</strong>：不需要额外的指针域，空间利用率高。</li><li><strong>缺点</strong>：容易产生聚集现象，导致查找效率下降。</li></ul></li><li><strong>链地址法</strong><ul><li><strong>原理</strong>：将所有哈希地址相同的元素都链接在同一个链表中。当发生冲突时，只需将元素插入到对应的链表中。</li><li><strong>优点</strong>：不易产生聚集现象，查找、插入和删除操作效率较高。</li><li><strong>缺点</strong>：需要额外的指针域，空间开销大。</li></ul></li></ul>', '<p>开放定址法是在哈希表内部寻找空闲位置，简单直接但可能导致元素聚集。链地址法通过链表来处理冲突，使元素分布更均匀，但增加了指针的存储开销。</p>', 5, '5', '查找');

-- ----------------------------
-- Table structure for schools_colleges
-- ----------------------------
DROP TABLE IF EXISTS `schools_colleges`;
CREATE TABLE `schools_colleges`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `colleges_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院名称',
  `university_id` int(255) NULL DEFAULT NULL COMMENT '学校编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '学院信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schools_colleges
-- ----------------------------
INSERT INTO `schools_colleges` VALUES (1, '电子与信息工程学院', 1);
INSERT INTO `schools_colleges` VALUES (2, '外国语学院', 1);
INSERT INTO `schools_colleges` VALUES (3, '电子工程与信息工程学院', 2);
INSERT INTO `schools_colleges` VALUES (4, '计算机与通信学院', 2);

-- ----------------------------
-- Table structure for schools_major
-- ----------------------------
DROP TABLE IF EXISTS `schools_major`;
CREATE TABLE `schools_major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `major_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业名称',
  `colleges_id` int(255) NULL DEFAULT NULL COMMENT '学院编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '专业信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schools_major
-- ----------------------------
INSERT INTO `schools_major` VALUES (1, '物联网工程', 1);
INSERT INTO `schools_major` VALUES (2, '计算机科学与技术', 4);

-- ----------------------------
-- Table structure for schools_university
-- ----------------------------
DROP TABLE IF EXISTS `schools_university`;
CREATE TABLE `schools_university`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校编码',
  `university_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校名称',
  `province_id` int(255) NULL DEFAULT NULL COMMENT '省',
  `city_id` int(255) NULL DEFAULT NULL COMMENT '市',
  `area_id` int(255) NULL DEFAULT NULL COMMENT '县',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '详细地址 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '学校信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schools_university
-- ----------------------------
INSERT INTO `schools_university` VALUES (1, '兰州交通大学', 28, 304, 2880, '安宁西路88号');
INSERT INTO `schools_university` VALUES (2, '兰州理工大学', 28, 304, 2878, '兰工坪路287号');
INSERT INTO `schools_university` VALUES (3, '西安交通大学', 27, 294, 2765, '朱雀大街205号');
INSERT INTO `schools_university` VALUES (4, '西安工业大学', 27, 294, 2764, '学府中路2号');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '密码',
  `key_sm3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码摘要',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '公钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (6, 'student', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.-UuSAtM_GHACvgnxzzvj7AHaHa?w=193&h=193&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202501162316039553', 'BDNPYwt5JDloILjvL1uuVLK9MM7uoIi+7uN61hkvEazwCb2wJgPApxwc4C9dTQFtaacGSEyxJ65jyRVNhQR3n9BItJT1KGRAP8pvf2GZ7aHITkHxzYjXmWPAEuEpBDzWqF7Jf8Z+lg==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '2563622159@qq.com', '马超', '男', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABFjleH3Rr+yiY9S+0Q/hyG6rN1u0Xgl1lEZmiCETl4qj5KMlrqWk6VJiWV3ku9hB6DnB1gSw9677pvY+snIYVJc=');
INSERT INTO `student` VALUES (7, 'student', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.-UuSAtM_GHACvgnxzzvj7AHaHa?w=193&h=193&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202503022347029538', 'BPvlqCyVgL4foqw8nGdUgvdRIRPvAzLqVs2s4Ao72giQFz1LyKht2JC4re7jT9FGv/tsA27ML33S2Zkg5cfK3RJ6tZGyUoeApsJxa2qlWAjrwAqPVX48WE7gW5tR5wNUrWjyxw+X1A==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '626692428@qq.com', '王青云', '男', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABES5pO5yRGW6riDfVUTMUbBGkbt6F9sByjLrmxUG2f27y6lmOBK47Kby3z3heVtnWoxUvBsMP2oIRQ2G6YlB+DE=');
INSERT INTO `student` VALUES (8, 'student', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.-UuSAtM_GHACvgnxzzvj7AHaHa?w=193&h=193&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202503022348007079', 'BIpGvrPgkLVbY6919HtGzjXtlejuUiDq2e9GPfRrqBuvGtoJYLpam22aQrT0H8mUSCVMlRmADcKqNy1q/4jra+KeOOKTi9cAOcrpAZz+yKDkPCe8ZIJ6+HVDErwtCjLo67df3NmHZw==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '626692428@qq.com', '高思琪', '女', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABITSmE7dh4UJIbZWj+cGr0AXIMV5u41w0+4uLRwEdUFzF/CIVq2bwK3I6AVStoSku+q43CnnjuihhuQpQdc9Seg=');

-- ----------------------------
-- Table structure for student_face
-- ----------------------------
DROP TABLE IF EXISTS `student_face`;
CREATE TABLE `student_face`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `face_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '人脸唯一标识',
  `face_feature` blob NULL COMMENT '人脸特征',
  `face_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '人脸照片路径',
  `id_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证照片路径',
  `province_id` int(11) NULL DEFAULT NULL COMMENT '省id',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '市id',
  `area_id` int(11) NULL DEFAULT NULL COMMENT '县id',
  `university_id` int(11) NULL DEFAULT NULL COMMENT '学校id',
  `colleges_id` int(11) NULL DEFAULT NULL COMMENT '学院id',
  `major_id` int(11) NULL DEFAULT NULL COMMENT '专业id',
  `is_submit` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份认证审核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '人脸认证' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_face
-- ----------------------------
INSERT INTO `student_face` VALUES (3, 6, '66edydu00xii3z1rozhffq51uopjqs', 0x0080FA440000A041C7089B3D30E4833D5C6B6DBD64FD043E48B257BD1E64D3BB10544BBD6FE40DBD4456C7BC4B19D63CD2378C3D744BD33C9EE602BCD49D34BD202135BEBDC239BD8E372FBD180737BE72EB86BC35F564BD752921BCC1B065BDD366DFBD3C3B95BB0DC287BDD4F0523D0DF2C1BD4657063C75F3ACBD47E2303E3BBA413DD784A6BCFD06293DBC5A913D2852BFBC523080BD968E043E41474F3DE117213DE72D263CD613A43BA273F03D7F698B3B43F5693B933A32BED86806BD4816C7BC1CB6B03D73F300BD64DF583D591169BD9A2125BDAB39C0BB0EEBA3BDE73DD0BCE42AEF3C402682BDC1FDE03D92C55BBDEE7D133E7619E33DEED77B3D1200E1BC04B82ABCB6CB973CAB6751BD06BA48BD927DEB3D845A2A3D8FC53A390C016ABCBA93043D6C09713DE8BF01BD802F0C3D29CF7EBD202C5BBDB00BD53C07E7013DF72AE03CC157553DB19CC0BC8C9616BCA19191BCB94917BDB4F617BDB9F8123EF7FD593EAAF4D5BC838F943C9B544E3DC1CFF8BD2CDA3FBDC53694BC8FF117BD27CA143C40E228BE2A499BBA1DE1A2BC164614BDE130293D853A9D3DADF57C3CCE712ABDECCB49BC2D7D473EDEB3F2BD9D15383BF6E7993DF6E0723D950569BD4DE3B63C9235CE3AF9C754BD30C4E938471CD63D2008AB3D7430E1BD3EB6773D9282DA3D1D8639BCD8A625BC4ABF3A3D2FCCFC3ABCD518BC62DAFCBC66D5A0BB18D3563D6F49503D58EF7D3D8B9CC0BCE307FC3CDB577B3DA618DB3C8B2E203AF9A0B03CB6EECBBD9A24A8BC8460833D1F283D3D7F11A7BC18975CBC910018BC52E223BDE7BB6ABC01E3B2BDCA62DE3A8E6E853D547AFD3DAA3BEEBC0B41D6BD000C563D46A2F7BA6C452CBE09E4C63BDB74EE3B16D4A13D1139C03CE0C226BC7ABF02BD9C8BAABD57ACB8BAEFC337BD0ADCAD3B9E8391BDAAAEC3BC1E0BE0BDBA62F3BD0F14253DFC8E103CE433D8BC7329C23C920E663DB0C6ED3C2678E93CF7549CBB0270BCBA455934BD3800333D73BFCF3CC4FF293D07D42DBC63D54EBDE8B809BD8E4D2F3DAF6C873BF5C78A3C9C10933D990782BDF78E03BCE3C8423C33C2B2BD159EC43B1B8CC93D4F43AF3D88049E3CFAAD01BCE84AA33D520551BC0308C83D0D8381BD7BE091BD773DBA3D19CD85BCC250C23C6E22FB3B77C66B3C64C24BBD8DE6403DCC17EDBC5B8734BD5FA9013EB5D9233DC68786BD3DC2DC3CA7AE9BBBE62681BD8131E4BC17B1C4BD696D06BCDD2A8C3C92E4B0BC5FEBCEBC5E4284BCDCF751BD5170C8BBEE8597BDA1146CBDCDBDD53DC21BAA3DB2E550BD2EE2AD3CBFA61DBD0E2881BDC37EB93D2383823CC0D2553CC59CA2BCA0572CBD403E8A3C4FA3A43D062F83BD8259AE3BF4E78DBC7565BB3D8913593DAE0C11BDC9CF59BD026198BD5F25173D0A790D3DDC5804BC9B02F5BD983F73BD8076AF3901CA57BC, 'http://localhost:9998/files/realFace/ee5885c6-a17b-4ad9-944f-740c8c9dfe27.jpg', 'http://localhost:9998/files/idPhoto/1742803591515-LS20240823132857.png', 28, 308, 2901, 1, 1, 1, '同意');
INSERT INTO `student_face` VALUES (5, 7, '2273icf1f49iqlrowjrkzbb9uagv8g', 0x0080FA440000A04190D6263E4E3B38BB75555CBD1921983C02DE913DD04D883B6530FE3C3E4BA43DBC01413D0F1CE73C66775D3CE77999BD350ADA3B0566FCBC476F7ABD4F3F04BD29E3D6BCEB327A3D84E4133DD14CB33D8093923D056962BD599B6DBD7FB7F43D77665E3CCC2402BE514DEDBC1A61D83DDBE3603D38CF0B3EEC1FB83D7EBA993D7AACA1BD8CB6E9BC1B08433C99019FBD81DE5F3BC611A3BB1043C33A796704BD55A5913D017ECA3D4E09F5BD6254E73C4C45C5BCCCB3E2BD76E911BC0E60D9BC560408BEC74DDFBC2E73C1BC7ECA39BA0E21E13C1C6B57BE2EDFD33D691148BC2BB2BBBC9B2380BC943093BD7F87633C811C943D29623A3E47DBD2BC5A53D13C5C29B73C3A1AC63D17A1BEBD846329BD36DD303D95D9553BC10BDF3DBF12E33D947E05BC2FA586BD1CBF16BCFE190E3C5F7F16BEC29D493D002D75BD0426D73C770DE4BD85D2593D8D1410BD919A3B3D22D964BD12C68D3D21473E3D114179BDEDC316BDEF5A603D42BCC53C5616A6BD300A09BE254F0D3E3BC0BA3D95A31ABD7F30C13C5655553D4153C9BB55A7293B4A76BFBD608D24BCD520563D2B2CA3BDB44F88BC5A6383BC873CB63D3F4F3BBDB830833AAE1E9EBD04EC37BDCF36863DBA60E63DE2DF203E06D4D53B94F9443C77B812BEF407563D9FBA073CD9E8A13C4E980DBD96BE733D1990023D1CC27ABDA595BA3DC265D33D40B832BD09EF53BDBC00A53DF4EE8F3DF95C7D3D950510BD3478393CD710F33D3ECB6D3D83168CBD35F96BBDB70C0A3D21A71F3E5BC8AE3C59CF003E4420B03D06D3AB3DA6E1B3BC96B25B3AEECBFE3CE0A90D3D327A70BD5A83ADBD2782A63CA0FB213EB47FA43CAC94AEBDD966A73D26E621BCF71FBE3D664832BBC5B03E3DEF3423BE01FAF8BD000BCEBCFB31993CEB062B3DEE6B023CB54646BD6F161E3D866B3DBD6518D83D1403783C6F6B5BBD46538ABC2B931EBB1575B8BBD7383CBD4BBAA4BC1AF9243CDB3ED83A308B9DBD2B94C6BDC9C9E73C6C4945BDC768ADBDAFC8B1BD097906BD0B497A3CBC0C14BBDC3C913C8603D23DC660DCBC17E1A73DEC80CB3DAAE64EBD4B34A1BDA4F009BD997A063DF757DABD12D437BDFB56E83DA45C243D3025E43C251C98BCFE1701BE5539BA3C993F9EBCAB36ED3C75188A3C4794ABBBBC2064BD5A86363DCFBFEC3DCF53AC3CDA95493D24F405BCA4F64A3CEE5F8F3BF0B2C0BD0BD796BD7B0CB73C84BFBBBDBF7CAD3C193AD0BD05861DB91E2C56BDEC690A3CC7667CBDCCE7513B351C753DE9F2403C35B2EC3DE04AF0BCBAD4A4BD8664A6BD62F3F4BB9C9B33BDFD6D8C3CFF590E3D85C1D63DFC6CCB3B22B3313DD617593DE5A6003EF080E7BC873FC9BCFCFB163B41A2FC3BE166BD3DC2393EBD9774123DF5628BBBC52130BD85A2AD3DCC5D1E3D29F3ABBCD6EA18BC29E28FBD62FF923D, 'http://localhost:9998/files/realFace/fb6a2799-e6a7-4f4f-8b07-f8cf71f6cc85.jpg', 'http://localhost:9998/files/idPhoto/1743169975832-微信图片_20231101225844.jpg', 1, 1, 1, 1, 1, 1, '同意');

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '日志名称',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '日志时间',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 367 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '日志信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES (212, 16, 'admin', 'admin', '用户登录', '2025-01-23 00:29:45', '192.168.10.7');
INSERT INTO `system_log` VALUES (213, 6, '马超', 'student', '用户登录', '2025-01-23 00:34:57', '192.168.10.7');
INSERT INTO `system_log` VALUES (214, 1, '机构成员A', 'caInstitution', '用户登录', '2025-02-08 14:15:17', '192.168.10.7');
INSERT INTO `system_log` VALUES (216, 1, '机构成员A', 'caInstitution', '用户登录', '2025-02-08 14:27:09', '192.168.10.7');
INSERT INTO `system_log` VALUES (217, 4, '马文', 'teacher', '用户登录', '2025-02-15 19:50:51', '192.168.10.6');
INSERT INTO `system_log` VALUES (219, 6, '马超', 'student', '用户登录', '2025-02-16 00:42:45', '192.168.10.6');
INSERT INTO `system_log` VALUES (220, 4, '马文', 'teacher', '用户登录', '2025-02-16 10:30:35', '192.168.10.6');
INSERT INTO `system_log` VALUES (221, 4, '马文', 'teacher', '用户登录', '2025-02-16 11:39:41', '192.168.10.6');
INSERT INTO `system_log` VALUES (223, 6, '马超', 'student', '用户登录', '2025-02-16 13:17:03', '192.168.10.6');
INSERT INTO `system_log` VALUES (224, 6, '马超', 'student', '用户登录', '2025-02-16 13:33:37', '192.168.10.6');
INSERT INTO `system_log` VALUES (225, 4, '马文', 'teacher', '用户登录', '2025-02-18 22:49:34', '192.168.10.6');
INSERT INTO `system_log` VALUES (226, 6, '马超', 'student', '用户登录', '2025-02-18 22:53:09', '192.168.10.6');
INSERT INTO `system_log` VALUES (227, 4, '马文', 'teacher', '用户登录', '2025-02-19 18:48:17', '192.168.10.6');
INSERT INTO `system_log` VALUES (228, 6, '马超', 'student', '用户登录', '2025-02-19 18:53:00', '192.168.10.6');
INSERT INTO `system_log` VALUES (229, 6, '马超', 'student', '用户登录', '2025-02-19 22:37:56', '192.168.10.6');
INSERT INTO `system_log` VALUES (230, 16, 'admin', 'admin', '用户登录', '2025-02-19 22:43:20', '192.168.10.6');
INSERT INTO `system_log` VALUES (231, 6, '马超', 'student', '用户登录', '2025-02-19 22:56:09', '192.168.10.6');
INSERT INTO `system_log` VALUES (232, 4, '马文', 'teacher', '用户登录', '2025-02-20 01:36:40', '192.168.10.6');
INSERT INTO `system_log` VALUES (233, 6, '马超', 'student', '用户登录', '2025-02-20 01:43:59', '192.168.10.6');
INSERT INTO `system_log` VALUES (234, 16, 'admin', 'admin', '用户登录', '2025-02-20 11:21:29', '192.168.10.6');
INSERT INTO `system_log` VALUES (235, 4, '马文', 'teacher', '用户登录', '2025-02-20 17:40:37', '192.168.10.6');
INSERT INTO `system_log` VALUES (236, 6, '马超', 'student', '用户登录', '2025-02-23 21:16:11', '192.168.1.9');
INSERT INTO `system_log` VALUES (237, 4, '马文', 'teacher', '用户登录', '2025-02-23 21:25:52', '192.168.1.9');
INSERT INTO `system_log` VALUES (238, 6, '马超', 'student', '用户登录', '2025-02-23 21:26:30', '192.168.1.9');
INSERT INTO `system_log` VALUES (239, 4, '马文', 'teacher', '用户登录', '2025-02-24 21:25:08', '192.168.1.9');
INSERT INTO `system_log` VALUES (240, 6, '马超', 'student', '用户登录', '2025-02-24 21:48:56', '192.168.1.9');
INSERT INTO `system_log` VALUES (241, 4, '马文', 'teacher', '用户登录', '2025-02-24 21:52:34', '192.168.1.9');
INSERT INTO `system_log` VALUES (242, 6, '马超', 'student', '用户登录', '2025-02-25 19:57:03', '192.168.1.14');
INSERT INTO `system_log` VALUES (243, 4, '马文', 'teacher', '用户登录', '2025-02-26 17:32:41', '192.168.1.8');
INSERT INTO `system_log` VALUES (244, 1, '机构成员A', 'caInstitution', '用户登录', '2025-02-26 23:05:44', '192.168.1.8');
INSERT INTO `system_log` VALUES (245, 4, '马文', 'teacher', '用户登录', '2025-02-27 13:33:39', '192.168.1.13');
INSERT INTO `system_log` VALUES (246, 6, '马超', 'student', '用户登录', '2025-02-27 13:34:32', '192.168.1.13');
INSERT INTO `system_log` VALUES (247, 4, '马文', 'teacher', '用户登录', '2025-03-01 10:51:34', '192.168.1.11');
INSERT INTO `system_log` VALUES (248, 6, '马超', 'student', '用户登录', '2025-03-01 11:38:16', '192.168.1.11');
INSERT INTO `system_log` VALUES (249, 6, '马超', 'student', '用户登录', '2025-03-01 20:03:49', '192.168.1.11');
INSERT INTO `system_log` VALUES (250, 4, '马文', 'teacher', '用户登录', '2025-03-01 20:05:21', '192.168.1.11');
INSERT INTO `system_log` VALUES (251, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-02 15:06:34', '192.168.1.14');
INSERT INTO `system_log` VALUES (252, 4, '马文', 'teacher', '用户登录', '2025-03-02 15:34:09', '192.168.1.14');
INSERT INTO `system_log` VALUES (253, 6, '马超', 'student', '用户登录', '2025-03-02 20:15:19', '192.168.1.14');
INSERT INTO `system_log` VALUES (254, 4, '马文', 'teacher', '用户登录', '2025-03-02 21:54:54', '192.168.1.14');
INSERT INTO `system_log` VALUES (255, 6, '马超', 'student', '用户登录', '2025-03-02 22:01:17', '192.168.1.14');
INSERT INTO `system_log` VALUES (256, 5, '刘诗慧', 'teacher', '用户登录', '2025-03-02 22:53:27', '192.168.1.14');
INSERT INTO `system_log` VALUES (257, NULL, NULL, NULL, '用户登录', '2025-03-02 23:08:13', '192.168.1.14');
INSERT INTO `system_log` VALUES (258, 4, '马文', 'teacher', '用户登录', '2025-03-02 23:08:29', '192.168.1.14');
INSERT INTO `system_log` VALUES (259, 5, '刘诗慧', 'teacher', '用户登录', '2025-03-02 23:44:52', '192.168.1.14');
INSERT INTO `system_log` VALUES (260, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-02 23:45:41', '192.168.1.14');
INSERT INTO `system_log` VALUES (261, 8, '高思琪', 'student', '用户登录', '2025-03-02 23:48:32', '192.168.1.14');
INSERT INTO `system_log` VALUES (262, 4, '马文', 'teacher', '用户登录', '2025-03-03 14:06:00', '192.168.1.15');
INSERT INTO `system_log` VALUES (263, 6, '马超', 'student', '用户登录', '2025-03-04 15:55:59', '10.11.23.46');
INSERT INTO `system_log` VALUES (264, 4, '马文', 'teacher', '用户登录', '2025-03-04 19:32:32', '192.168.1.4');
INSERT INTO `system_log` VALUES (265, 6, '马超', 'student', '用户登录', '2025-03-05 11:53:00', '192.168.1.12');
INSERT INTO `system_log` VALUES (266, 4, '马文', 'teacher', '用户登录', '2025-03-05 22:30:51', '192.168.1.12');
INSERT INTO `system_log` VALUES (267, NULL, NULL, NULL, '用户登录', '2025-03-05 22:32:42', '192.168.1.12');
INSERT INTO `system_log` VALUES (268, NULL, NULL, NULL, '用户登录', '2025-03-05 22:32:48', '192.168.1.12');
INSERT INTO `system_log` VALUES (269, 6, '马超', 'student', '用户登录', '2025-03-05 22:32:54', '192.168.1.12');
INSERT INTO `system_log` VALUES (270, 4, '马文', 'teacher', '用户登录', '2025-03-06 16:50:30', '192.168.1.4');
INSERT INTO `system_log` VALUES (271, 6, '马超', 'student', '用户登录', '2025-03-06 17:20:18', '192.168.1.4');
INSERT INTO `system_log` VALUES (272, 4, '马文', 'teacher', '用户登录', '2025-03-07 23:59:32', '192.168.1.9');
INSERT INTO `system_log` VALUES (273, 6, '马超', 'student', '用户登录', '2025-03-08 16:06:12', '127.0.0.1');
INSERT INTO `system_log` VALUES (274, 4, '马文', 'teacher', '用户登录', '2025-03-08 16:23:01', '127.0.0.1');
INSERT INTO `system_log` VALUES (275, 6, '马超', 'student', '用户登录', '2025-03-08 16:44:50', '127.0.0.1');
INSERT INTO `system_log` VALUES (276, 6, '马超', 'student', '用户登录', '2025-03-08 17:04:15', '10.11.23.46');
INSERT INTO `system_log` VALUES (277, NULL, NULL, NULL, '用户登录', '2025-03-08 17:30:23', '10.11.23.46');
INSERT INTO `system_log` VALUES (278, 4, '马文', 'teacher', '用户登录', '2025-03-08 17:30:32', '10.11.23.46');
INSERT INTO `system_log` VALUES (279, 8, '高思琪', 'student', '用户登录', '2025-03-09 19:39:33', '192.168.1.13');
INSERT INTO `system_log` VALUES (280, 8, '高思琪', 'student', '用户登录', '2025-03-09 19:39:35', '192.168.1.13');
INSERT INTO `system_log` VALUES (281, NULL, NULL, NULL, '用户登录', '2025-03-09 20:02:03', '192.168.1.13');
INSERT INTO `system_log` VALUES (282, 4, '马文', 'teacher', '用户登录', '2025-03-09 20:02:10', '192.168.1.13');
INSERT INTO `system_log` VALUES (283, 5, '刘诗慧', 'teacher', '用户登录', '2025-03-10 16:24:41', '192.168.1.17');
INSERT INTO `system_log` VALUES (284, 4, '马文', 'teacher', '用户登录', '2025-03-10 17:18:23', '192.168.1.17');
INSERT INTO `system_log` VALUES (285, NULL, NULL, NULL, '用户登录', '2025-03-10 18:35:53', '192.168.1.17');
INSERT INTO `system_log` VALUES (286, 4, '马文', 'teacher', '用户登录', '2025-03-10 18:35:59', '192.168.1.17');
INSERT INTO `system_log` VALUES (287, 6, '马超', 'student', '用户登录', '2025-03-11 15:14:45', '10.11.2.29');
INSERT INTO `system_log` VALUES (288, 4, '马文', 'teacher', '用户登录', '2025-03-11 15:19:54', '10.11.2.29');
INSERT INTO `system_log` VALUES (289, 4, '马文', 'teacher', '用户登录', '2025-03-13 12:22:15', '192.168.1.13');
INSERT INTO `system_log` VALUES (290, NULL, NULL, NULL, '用户登录', '2025-03-15 14:30:09', '192.168.1.10');
INSERT INTO `system_log` VALUES (291, 4, '马文', 'teacher', '用户登录', '2025-03-15 14:30:42', '192.168.1.10');
INSERT INTO `system_log` VALUES (292, 6, '马超', 'student', '用户登录', '2025-03-16 15:34:40', '192.168.1.9');
INSERT INTO `system_log` VALUES (293, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-16 16:15:07', '192.168.1.9');
INSERT INTO `system_log` VALUES (294, 16, 'admin', 'admin', '用户登录', '2025-03-16 17:31:08', '192.168.1.9');
INSERT INTO `system_log` VALUES (295, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-16 21:30:34', '192.168.1.9');
INSERT INTO `system_log` VALUES (296, NULL, NULL, NULL, '用户登录', '2025-03-16 21:40:59', '192.168.1.9');
INSERT INTO `system_log` VALUES (297, 16, 'admin', 'admin', '用户登录', '2025-03-16 21:41:07', '192.168.1.9');
INSERT INTO `system_log` VALUES (298, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-16 22:47:29', '192.168.1.9');
INSERT INTO `system_log` VALUES (299, 4, '马文', 'teacher', '用户登录', '2025-03-16 22:50:18', '192.168.1.9');
INSERT INTO `system_log` VALUES (300, 16, 'admin', 'admin', '用户登录', '2025-03-17 22:32:14', '192.168.1.10');
INSERT INTO `system_log` VALUES (301, 6, '马超', 'student', '用户登录', '2025-03-17 22:35:00', '192.168.1.10');
INSERT INTO `system_log` VALUES (302, 4, '马文', 'teacher', '用户登录', '2025-03-17 22:39:45', '192.168.1.10');
INSERT INTO `system_log` VALUES (303, 6, '马超', 'student', '用户登录', '2025-03-18 13:27:39', '192.168.1.12');
INSERT INTO `system_log` VALUES (304, 4, '马文', 'teacher', '用户登录', '2025-03-18 16:50:06', '192.168.198.53');
INSERT INTO `system_log` VALUES (305, 6, '马超', 'student', '用户登录', '2025-03-20 14:20:45', '192.168.1.14');
INSERT INTO `system_log` VALUES (306, NULL, NULL, NULL, '用户登录', '2025-03-20 15:04:27', '192.168.1.14');
INSERT INTO `system_log` VALUES (307, 16, 'admin', 'admin', '用户登录', '2025-03-20 15:04:35', '192.168.1.14');
INSERT INTO `system_log` VALUES (308, 6, '马超', 'student', '用户登录', '2025-03-20 20:41:26', '192.168.1.14');
INSERT INTO `system_log` VALUES (309, 6, '马超', 'student', '用户登录', '2025-03-21 00:11:00', '192.168.8.107');
INSERT INTO `system_log` VALUES (310, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-21 00:47:06', '192.168.8.107');
INSERT INTO `system_log` VALUES (311, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-21 10:48:03', '10.11.5.224');
INSERT INTO `system_log` VALUES (312, 6, '马超', 'student', '用户登录', '2025-03-21 11:09:04', '10.11.5.224');
INSERT INTO `system_log` VALUES (313, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-21 23:28:10', '192.168.1.17');
INSERT INTO `system_log` VALUES (314, 6, '马超', 'student', '用户登录', '2025-03-22 21:19:46', '192.168.1.15');
INSERT INTO `system_log` VALUES (315, 3, '机构成员B', 'caInstitution', '用户登录', '2025-03-23 19:39:44', '192.168.1.14');
INSERT INTO `system_log` VALUES (316, NULL, NULL, NULL, '用户登录', '2025-03-23 23:43:06', '127.0.0.1');
INSERT INTO `system_log` VALUES (317, 4, '马文', 'teacher', '用户登录', '2025-03-23 23:43:14', '127.0.0.1');
INSERT INTO `system_log` VALUES (318, 6, '马超', 'student', '用户登录', '2025-03-24 00:41:58', '127.0.0.1');
INSERT INTO `system_log` VALUES (319, 4, '马文', 'teacher', '用户登录', '2025-03-24 16:43:49', '192.168.1.4');
INSERT INTO `system_log` VALUES (320, 8, '高思琪', 'student', '用户登录', '2025-03-25 23:34:22', '192.168.1.20');
INSERT INTO `system_log` VALUES (321, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-27 12:51:32', '192.168.8.107');
INSERT INTO `system_log` VALUES (322, 6, '马超', 'student', '用户登录', '2025-03-27 16:39:21', '192.168.8.107');
INSERT INTO `system_log` VALUES (323, 6, '马超', 'student', '用户登录', '2025-03-27 17:43:34', '192.168.8.107');
INSERT INTO `system_log` VALUES (324, 16, 'admin', 'admin', '用户登录', '2025-03-27 17:52:23', '192.168.8.107');
INSERT INTO `system_log` VALUES (325, 1, '机构成员A', 'caInstitution', '用户登录', '2025-03-27 19:49:14', '192.168.8.107');
INSERT INTO `system_log` VALUES (326, 16, 'admin', 'admin', '用户登录', '2025-03-27 20:28:10', '192.168.8.107');
INSERT INTO `system_log` VALUES (327, 4, '马文', 'teacher', '用户登录', '2025-03-27 21:28:09', '192.168.8.107');
INSERT INTO `system_log` VALUES (328, 4, '马文', 'teacher', '用户登录', '2025-03-28 13:54:33', '192.168.1.18');
INSERT INTO `system_log` VALUES (329, 6, '马超', 'student', '用户登录', '2025-03-28 20:44:50', '192.168.1.18');
INSERT INTO `system_log` VALUES (330, 4, '马文', 'teacher', '用户登录', '2025-03-28 21:04:49', '192.168.1.18');
INSERT INTO `system_log` VALUES (331, 6, '马超', 'student', '用户登录', '2025-03-28 21:10:47', '192.168.1.18');
INSERT INTO `system_log` VALUES (332, 7, '王青云', 'student', '用户登录', '2025-03-28 21:51:53', '192.168.1.18');
INSERT INTO `system_log` VALUES (333, 3, '机构成员B', 'caInstitution', '用户登录', '2025-03-28 21:58:54', '192.168.1.18');
INSERT INTO `system_log` VALUES (334, 4, '马文', 'teacher', '用户登录', '2025-03-28 21:59:39', '192.168.1.18');
INSERT INTO `system_log` VALUES (335, 5, '刘诗慧', 'teacher', '用户登录', '2025-03-28 22:22:00', '192.168.1.18');
INSERT INTO `system_log` VALUES (336, 6, '李安', 'teacher', '用户登录', '2025-03-28 22:23:10', '192.168.1.18');
INSERT INTO `system_log` VALUES (337, 4, '马文', 'teacher', '用户登录', '2025-03-28 22:24:09', '192.168.1.18');
INSERT INTO `system_log` VALUES (338, 6, '马超', 'student', '用户登录', '2025-03-28 22:27:19', '192.168.1.18');
INSERT INTO `system_log` VALUES (339, 6, '马超', 'student', '用户登录', '2025-04-03 17:29:12', '10.11.6.51');
INSERT INTO `system_log` VALUES (340, 4, '马文', 'teacher', '用户登录', '2025-04-03 17:32:12', '10.11.6.51');
INSERT INTO `system_log` VALUES (341, 1, '机构成员A', 'caInstitution', '用户登录', '2025-04-04 21:00:05', '192.168.1.5');
INSERT INTO `system_log` VALUES (342, 16, 'admin', 'admin', '用户登录', '2025-04-04 21:12:13', '192.168.1.5');
INSERT INTO `system_log` VALUES (343, 4, '马文', 'teacher', '用户登录', '2025-04-04 21:44:38', '192.168.1.5');
INSERT INTO `system_log` VALUES (344, 6, '马超', 'student', '用户登录', '2025-04-04 22:04:34', '192.168.1.5');
INSERT INTO `system_log` VALUES (345, 4, '马文', 'teacher', '用户登录', '2025-04-08 16:50:25', '10.11.22.88');
INSERT INTO `system_log` VALUES (346, 6, '马超', 'student', '用户登录', '2025-04-13 14:47:37', '192.168.1.16');
INSERT INTO `system_log` VALUES (347, 7, '王青云', 'student', '用户登录', '2025-04-13 14:50:04', '192.168.1.16');
INSERT INTO `system_log` VALUES (348, 8, '高思琪', 'student', '用户登录', '2025-04-13 14:50:36', '192.168.1.16');
INSERT INTO `system_log` VALUES (349, 1, '机构成员A', 'caInstitution', '用户登录', '2025-04-13 14:55:59', '192.168.1.16');
INSERT INTO `system_log` VALUES (350, NULL, NULL, NULL, '用户登录', '2025-04-13 15:27:37', '192.168.1.16');
INSERT INTO `system_log` VALUES (351, 16, 'admin', 'admin', '用户登录', '2025-04-13 15:28:12', '192.168.1.16');
INSERT INTO `system_log` VALUES (352, 4, '马文', 'teacher', '用户登录', '2025-04-13 15:47:31', '192.168.1.16');
INSERT INTO `system_log` VALUES (353, 6, '马超', 'student', '用户登录', '2025-04-13 15:49:12', '192.168.1.16');
INSERT INTO `system_log` VALUES (354, 16, 'admin', 'admin', '用户登录', '2025-04-13 15:56:07', '192.168.1.16');
INSERT INTO `system_log` VALUES (355, 6, '马超', 'student', '用户登录', '2025-04-13 16:19:29', '192.168.1.16');
INSERT INTO `system_log` VALUES (356, 1, '机构成员A', 'caInstitution', '用户登录', '2025-04-13 20:49:27', '192.168.1.16');
INSERT INTO `system_log` VALUES (357, NULL, NULL, NULL, '用户登录', '2025-04-13 22:21:37', '192.168.1.16');
INSERT INTO `system_log` VALUES (358, 4, '马文', 'teacher', '用户登录', '2025-04-13 22:21:49', '192.168.1.16');
INSERT INTO `system_log` VALUES (359, 6, '马超', 'student', '用户登录', '2025-04-13 22:27:28', '192.168.1.16');
INSERT INTO `system_log` VALUES (360, 4, '马文', 'teacher', '用户登录', '2025-04-13 22:39:54', '192.168.1.16');
INSERT INTO `system_log` VALUES (361, 6, '马超', 'student', '用户登录', '2025-04-13 22:47:49', '192.168.1.16');
INSERT INTO `system_log` VALUES (362, 4, '马文', 'teacher', '用户登录', '2025-04-13 22:53:50', '192.168.1.16');
INSERT INTO `system_log` VALUES (363, 6, '马超', 'student', '用户登录', '2025-04-14 20:40:35', '192.168.1.16');
INSERT INTO `system_log` VALUES (364, 4, '马文', 'teacher', '用户登录', '2025-04-14 22:54:04', '192.168.1.16');
INSERT INTO `system_log` VALUES (365, 6, '马超', 'student', '用户登录', '2025-04-15 21:28:02', '192.168.1.19');
INSERT INTO `system_log` VALUES (366, 4, '马文', 'teacher', '用户登录', '2025-04-18 01:29:37', '127.0.0.1');

-- ----------------------------
-- Table structure for system_news
-- ----------------------------
DROP TABLE IF EXISTS `system_news`;
CREATE TABLE `system_news`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(255) NULL DEFAULT NULL COMMENT '发布者',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '新闻标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '新闻内容',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '新闻时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '新闻表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_news
-- ----------------------------
INSERT INTO `system_news` VALUES (23, 16, '以考促学、以学促用，坚持把学习教育贯彻始终', '<p>平安鄂尔多斯</p><p>2021-05-18 18:03</p><p>来源：澎湃新闻·澎湃号·政务</p><p>&nbsp;∙&nbsp;<a target=\"_blank\" href=\"https://www.thepaper.cn/list_27392\">澎湃号·政务&nbsp;&gt;</a></p><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/144.jpg\"/><img src=\"http://localhost:9998/files/wang/1743070813195-85810308-image.png\" style=\"max-width:100%;\" contenteditable=\"false\"/><br/><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/144.jpg\"/></p><p>为推动公安队伍教育整顿工作走深走实，夯实全警思想根基，检验学习教育成果，确保理论学习“不走形式、不走过场”，学习内容入脑入心，鄂尔多斯市公安局组织开展教育整顿应知应会知识集中测试活动。</p><p>纪委党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/148.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/148.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070836467-08644298-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><br/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/150.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/150.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070846605-95709573-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>4月12日，纪委党支部组织全体党员民警开展“你问我答测试”主题党日活动，陶安良副局长作为普通党员参加了活动。活动中，全体党员民警对相关应会知识进行测试。此次知识竞赛主题鲜明，形式新颖，效果良好，提升了大家的参与度和投入度，在愉悦的氛围中学到了知识，活动圆满成功。通过测试，党员民警增强了党建知识、政治理论学习的主动性，鼓舞了干事创业的精气神，锤炼了忠诚干净担当的政治品格。</p><p>政治部党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/165.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/165.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070859559-23301334-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/181.jpg\"/><img src=\"http://localhost:9998/files/wang/1743070869028-37549423-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/181.jpg\"/></p><p>为确保学习教育环节各项内容高质量完成、全面检验学习成果。政治部党支部组织开展教育整顿学习教育环节理论知识测试，全体民警辅警参加集中考试。此次考试采取闭卷的方式进行，测试的主要内容为公安队伍教育整顿“应知应会”知识，设置填空题和简答题两种题型，通过学习以及测试检验民警辅警对公安队伍教育整顿学习教育环节部署内容是否入脑入心，不断提升思想认识、文化素养和综合素质。考场考风严肃、考纪严明，参考人员全神贯注、认真作答，以认真严谨的态度对待此次教育整顿知识测试。通过测试检验了学习成果，进一步强化了全体民警辅警对队伍教育整顿工作的认识和理解，巩固了相关理论知识，提高了思想认识，增强了加紧学习的紧迫感，为下一步有效推动公安队伍教育整顿深入开展奠定了良好的思想基础。</p><p>情指中心党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/182.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/182.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070881894-44242363-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><br/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/184.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/184.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070893564-09433213-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/186.jpg\"/></p><p>为推动队伍教育整顿工作走深走实，检验教育整顿学习教育成效，夯实民辅警思想根基，5月14日，情指中心组织开展队伍教育整顿应知应会知识测试，全体民辅警参加测试。测试中，全体参加测试人员自觉遵守考场纪律，考试过程严肃，考场秩序井然，展现出了良好的精神面貌。通过此次测试，全体民辅警摸排了理论知识盲点，提高了知晓率，进一步强化了学习自觉性，达到了以考促学，巩固了学习教育成果。</p><p>国保支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/190.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/190.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070904915-58559079-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>为深入推动政法队伍教育整顿学习教育走深走实，检验学习教育成果，确保理论学习“不走形式、不走过场”，学习内容入脑入心，国保支队利用晚学时间，组织开展队伍教育整顿应知应会知识集中测试活动，陈斯琴政委及12名在家民警辅警参加了测试。此次测试采用闭卷形式进行，试题以教育整顿以来的学习内容为主，突出“五个过硬”“三大主题”“四项任务”“三个环节”等重点知识，进一步加深了民警辅警对教育整顿应知应会内容的学习理解，达到“以考促学、以学促行、知行合一”的教育整顿目的。测试过程中，参考民警辅警态度端正，自觉遵守考场纪律，用心审题，认真答卷，均在规定时间内完成了测试。整个测试过程严肃紧张，井然有序，展现了国保民警优良的纪律作风和良好的精神风貌。</p><p>刑侦支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/196.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/196.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070914705-43457181-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/216.jpg\"/><img src=\"http://localhost:9998/files/wang/1743070923317-34550790-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/216.jpg\"/></p><p>根据市局统一部署安排，为扎实推动教育整顿工作走深走实，刑侦支队组织全体在岗民警对教育整顿学习内容进行知识测试。测试采取闭卷答题，由支部书记杨忠亮同志统一组织监考，限时完成考试，并统一对试卷进行了批改。考试完毕后，杨忠亮同志采用现场快问快答的形式了解同志们对教育整顿知识的掌握情况。他要求全体民警熟记考查知识，加强教育整顿应知应会内容的学习，做到入心入脑，随时应考，考即满分的理论水平。通过考试和现场快问快答，达到了以考促学，以理论考试检验学习成果的目的，同时以此能有效推动全体民警对教育整顿应知应会内容的强化学习。</p><p>反恐支队党支部</p><p>根据市局统一部署安排，为扎实推动教育整顿工作走深走实，5月14日组织全体在岗民警对教育整顿学习内容进行知识测试。测试采取闭卷答题，由支部书记赵春江同志统一组织，限时完成考试。考试完毕后由赵春江书记统一改判，并要求全体民警熟记考查知识，加强教育整顿应知应会内容的学习，做好随时应考，考即满分的理论水平。通过考试，达到了以考促学，以理论考试检验学习成果的目的，同时以此能有效推动全体民警对教育整顿应知应会内容的强化学习。</p><p>监管支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/231.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/231.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070934222-34606313-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/><br/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/242.jpg\"/><img src=\"http://localhost:9998/files/wang/1743070943016-56709351-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/242.jpg\"/></p><p>为深入贯彻落实全市公安队伍教育整顿动员部署会议精神，扎实开展好队伍教育整顿工作，按照市公安局党委安排部署，根据市公安局《鄂尔多斯市公安队伍教育整顿实施方案》和《关于开展党史学习教育的方案》的通知要求，监管支队开展了一次党史学习教育和公安机关教育整顿工作应知应会考试。本次考试要求监管支队全体同志要做到学史明理，学史增信，学史崇德，学史力行，学党史，悟思想，办实事，开新局，以昂扬姿态奋力开启全面建设社会主义现代化国家新征程，以优异成绩迎接建党一百周年。全体同志通过本次考试活动既加强了对党的认识，也对近期党史学习的成果进行了一次考验。</p><p>出入境管理处党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/258.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/258.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070951904-20731071-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><br/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/283.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/283.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070960837-15244945-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>为扎实推动党史学习教育和全市公安队伍教育整顿工作深入开展，全面检验队伍教育整顿学习教育阶段成效，出入境管理处按照市局要求，对全体民警、辅警进行了队伍教育整顿应知应会知识集中测试。为确保考试达到“以考促学、以考验学、以考固学”预期效果，出入境管理处精心组织，重点围绕全市政法队伍教育整顿应知应会和党史学习教育等内容展开。全体民警、辅警认真参与测试，排查自身知识点盲区，为下一步强化学习打基础。此次测试，是对出入境管理处全体民警、辅警教育整顿前期集中学习和个人自学成果的考核检验，强化了民警、辅警理论知识储备，为着力锻造一支“信念过硬、政治过硬、责任过硬、能力过硬、作风过硬”的高素质公安铁军筑牢思想根基。</p><p>交管支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/301.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/301.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070972066-50954539-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><br/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/313.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/313.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070980436-01160694-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>为检验支队机关全体民辅警队伍教育整顿学习成效，交管支队在市公安局二楼会议室组织机关全体民辅警开展队伍教育整顿理论知识测试。理论知识测试范围紧紧围绕教育整顿相关学习内容，结合党史学习教育及应知应会知识点，采取集中闭卷答题形式进行。参加考试的人员严守考场纪律，认真答题，在规定时间内完成了考试内容，测试达到了预期效果。通过测试，进一步加深了机关全体人员对开展教育整顿工作重要意义的认识，加强了全体人员对理论知识水平的掌握，提升了民警辅警的学习积极性，达到了“以考促学，以学促用”的目的，推动了队伍教育整顿走深走实。</p><p>法制支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/361.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/361.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743070991279-09151785-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/411.jpg\"/><img src=\"http://localhost:9998/files/wang/1743071005113-70460599-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/411.jpg\"/></p><p>为加强支队民警对教育整顿的精准认识和深度把握，检验民警对近期政治教育基本知识的理解和掌握情况，形成全警参与的良好氛围。法制支队组织召开会议，传达学习近期重点会议精神，分析和总结了教育整顿开展以来的工作情况，重点强调各岗位民警要做好学习笔记，按阶段完成好各项任务。会后，进行应知应会测试。考试内容主要以各级教育整顿会议及相关文件精神为主，着重考察民警对教育整顿相关基本知识的理解和掌握情况。考试过程中，全体参考人员认真作答、独立完成。通过此次测试，支队民警更加深刻地认识到教育整顿开展的重要性和必要性，为法制工作开展打好坚实的政治理论基础。</p><p>禁毒支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/417.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/417.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071012402-56408756-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>禁毒支队党支部利用晚学时间开展教育整顿阶段性工作测试党日活动。测试由高怀远政委发放试卷并监考，考试时间20分钟，禁毒支队党支部在家全体党员民警辅警参加了测试。全体民辅警认真作答了机关党委统一印发的《全市政法队伍教育整顿阶段性工作测试》试卷，测试结束后，支部全体支委委员对试卷进行集体改判卷。本次测试检验了支部民辅警的学习成果，提升了民辅警的学习积极性，达到了以考促学、以学促用的目的，为进一步推动禁毒支队党支部教育整顿工作深入开展奠定了坚实的基础。</p><p>警航支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/427.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/427.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071021132-31790498-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><br/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/437.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/437.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071029496-64710461-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>为深入推进教育整顿走深走实，检验教育整顿学习教育成效，夯实民辅警思想根基，警航支队组织全队进行第二次教育整顿应知应会知识测试，支队全体民辅警参加测试。本次测试采取闭卷形式进行，试题主要包括政法队伍教育整顿和党史学习的相关内容，知识点全面，内容针对性强。在测试过程中，参考人员自觉遵守考场纪律，认真答题，展现了良好的精神风貌。教育整顿应知应会知识测试常态化开展，监测了民警对近期学习应知应会内容的掌握和理解情况，进一步激发了民辅警学习的积极性和主动性，达到了以学促考强本领、以考促练砺精兵的效果。</p><p>特警支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/448.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/448.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071040017-37346903-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/457.jpg\"/></p><p>为检验和巩固全体队员党史学习教育和队伍教育整顿的学习成效，特警支队党支部组织开展全市政法队伍教育整顿阶段性工作测试，在位民警辅警参加测试。测试中，全体参考人员严格遵守考场纪律，严肃认真作答，均在时间内完成了答题，保证了测试效果。通过开展全市政法队伍教育整顿阶段性工作测试，加深了队员们对开展队伍教育整顿工作重要意义的认识，展示了特警支部广大民警辅警积极的学习态度和良好的纪律作风，提升了大家的思想政治理论水平，达到了“以考促学、以学促用、知行合一”深化学习的目的，为促进队伍教育整顿、履行好职责使命提供了坚强政治保证，真正做到了让教育整顿工作走深走实。</p><p>审计处党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/468.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/468.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071052979-16610418-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p>为进一步推动审计处党支部教育整顿深入开展，检验审计处党支部民警学习成效，审计处党支部组织全体民警开展了公安队伍教育整顿应知应会知识测试。本次测试紧紧围绕公安队伍教育整顿工作相关工作要求以及日常学习内容，采取闭卷答题的方式进行了测试，以检验民警对公安队伍教育整顿内容的掌握情况。本次测试检验了支部民警的学习成果，提升了民警的学习积极性，达到了以考促学、以学促用的目的，为进一步推动审计处党支部教育整顿工作深入开展奠定了坚实的思想基础。</p><p>食药支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/483.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/483.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071062523-59584916-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/491.jpg\"/></p><p>为深入推进全市公安机关队伍教育整顿工作，强化政治理论学习成果，食药支队组织全体在家民（辅）警开展了队伍教育整顿应知应会知识集中测试。本次测试共分为选择题、填空题、简答题三个模块，采取闭卷方式，测试内容紧扣队伍教育整顿目标任务，围绕教育整顿的学习内容和集中学习教育阶段的重点内容、重点安排进行出题，考察民（辅）警对教育整顿基本知识的掌握情况。答题过程中，全体参考人员认真审题、专心作答、严守考场纪律，在规定时间内完成了答题。此次应知应会知识测试是食药支队深化队伍教育整顿的具体抓手，通过开展测试，进一步检验了民（辅）警对教育整顿应知应会知识的掌握情况及学习成果，调动了民（辅）警学习的积极性、自觉性和主动性，达到了以考促学、以知促行、深化学习的目的，为深入推进学习教育扎实开展奠定了坚实基础。</p><p>旅警支队党支部</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/500.jpg\"/><img src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/517.jpg\" style=\"font-size: 14px;\"/><img src=\"http://localhost:9998/files/wang/1743071077980-83147554-image.png\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/517.jpg\"/><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/529.jpg\"/></p><p>为进一步扎实推进政法队伍教育整顿工作，强化政治理论学习成果，旅游警察支队组织支队全体民警、辅警开展了队伍教育整顿应知应会知识集中测试。旅警支队高度重视此次队伍教育整顿应知应会知识集中测试活动，认真组织全体民警、辅警进行考试。此次测试紧紧围绕全市政法队伍教育整顿应知应会等重点内容展开。测试期间，参考民警、辅警神情专注，认真审题，沉着应答，在规定时间内独立完成。本次政法队伍教育整顿应知应会知识测试全面考察了支队民警对相关知识点的掌握和理解情况，提升了民警的学习积极性、自觉性、主动性，达到了以考促学，以理论考试检验学习成果的目的。</p><p><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/542.jpg\"/><img alt=\"\" src=\"https://imagepphcloud.thepaper.cn/pph/image/132/395/549.jpg\"/></p><p>原标题：《以考促学、以学促用，坚持把学习教育贯彻始终》</p>', '2025-03-27 18:24:40');
INSERT INTO `system_news` VALUES (24, 16, '我校2023年全国硕士研究生招生考试初试工作圆满完成', '<p>我校2023年全国硕士研究生招生考试初试工作圆满完成</p><p>发布时间：2022-12-28&nbsp;来源：研究生院</p><p>2022年12月24日至25日，2023年全国硕士研究生招生考试初试如期举行。这是组考工作史上最难、最特殊的一次大考。为顺利完成“如期考试、应考尽考、平安研考”目标，圆满完成初试组考及考务工作，中央财经大学全校各单位、部门齐心协力、积极响应、迎难而上、向疫而行，全心全力为这场“史无前例”的研考交上圆满答卷。</p><p><img src=\"https://news.cufe.edu.cn/__local/D/BE/50/3D7165169430D596D8456A12E6F_F24F64C4_F13C0.png\"/></p><p><img src=\"https://news.cufe.edu.cn/__local/2/2B/3D/DF0D288AAB5DD059B8821067B30_687349C6_F9956.png\"/></p><p>▲考生有序进入考场</p><p>中央财经大学考点在学院南路校区和沙河校区共设置了136个普通考场，11个专用考场，同时按照10：3的比例设置备用考场。考点共迎来了3793名考生，其中学院南路校区77个考场、2137名考生，沙河校区59个考场、1656名考生。考试期间，共有监考人员和组织考务人员400余名、安保及后勤保障人员100余名到场，另有百余名教职工作为备用人员随时待命。</p><p>2023年研考历经两轮异地借考、分类型设置考生考场等重大挑战和全新难度，在教育部、北京市的统一安排部署下，学校领导高度重视，积极谋划、科学部署，校党委书记何秀超考前进行多项专题部署，校长王瑶琪，校党委副书记梁勇，校党委副书记、副校长马海涛巡视了我校学院南路和沙河两个考区，重点关注了考务组织和疫情防控等工作，实地视察了专用考场的启用情况和防控准备，慰问了保障研究生招生考试顺利进行的各部门工作人员，并通过“远程电子巡查系统”对两校区考场进行了巡查。考试期间，北京市委教育工委副书记沈千帆至我校考点进行巡查。全校各行政部门、研究生招生单位负责人积极践行一线规则，到各考务办驻场或者加入备用老师队伍，进行现场协调、调度。</p><p><img src=\"https://news.cufe.edu.cn/__local/3/CF/01/5D1707EB4F4FB8F7CB30B7EE633_17E21709_22E47.jpg\"/></p><p>上图：何秀超部署讲话</p><p>中图：王瑶琪、梁勇现场巡视</p><p>下图：沈千帆、马海涛现场巡视</p><p>此次考试是在“二十条”和“新十条”的背景下举办的全国性大型考试，为落实教育部、国家卫健委、国家疾控局“一类一策”相关要求，兼顾考试安全和疫情防控，保障研考平稳顺利进行，我校在考前进行了充分的准备工作，多次开展健康状况摸排调查，主动摸排、持续跟进、精准筛查考生信息，并按要求分类设置考场，将学院南路校区主教学楼、沙河校区东区主教学楼设置为普通考场，学院南路校区中财大厦、沙河校区西区主教学楼设置为专用考场，设置专用考场闭环通道。为保证专用考场考生顺利参加考试，直至23日深夜学校仍在逐一短信、电话通知相关考生。</p><p><img src=\"https://news.cufe.edu.cn/__local/8/A1/75/B0D7FE44916BC28CD517E73B990_C4CCC611_8643C.png\"/></p><p>▲专用考场情况</p><p>2023研考组织面临的另一重大难题便是监考及考务人员组织压力，考前每日均有相关工作人员因近期发烧、检测阳性而无法继续到岗，学校各单位、部门顶住人员压力，迅速进行人员调配，确保研究生招生考试各项任务的顺利开展。学校办公室为考试配备了充足的防疫物资并牵头协调防疫工作等；组织部、机关党委积极调动各行政部门骨干力量充实监考及考务后备队伍，为确保人员充足保驾护航；宣传部对研考各项准备进行了广泛宣传报道；纪委、监察处对考试组织相关工作人员进行了纪律教育并开展现场督察；学生处统筹协调安排了本校应届考生来往两校区相关工作；保卫处为考生入场制定了详实的方案，配备了测温棚、警戒线等防疫设备，并承担了考试期间的安全保卫工作；研究生院承担了此次研考的组考和疫情防控协调组织工作；财务处为社会考生餐饮服务提供了财务结算支持；团委积极招募学生志愿者协助考场布置；智慧校园建设中心确保了考试期间电子巡查系统的正常使用、考场规则的播放及其他电子设备、无线网络的管理；校医院积极协调卫生疾控部门，为有需求的考生提供现场抗原检测，并完成考试期间防疫和卫生医疗保障工作；后勤服务产业集团和中财大资产经营（北京）有限公司分别为学院南路校区和沙河校区完成考点布置，并对两校区所有考场做好消毒通风工作，对考生入校测量体温等；后勤服务产业集团还为考试工作人员及监考老师提供班车服务，为无校园卡考生提供热水和餐饮服务；各招生单位负责人在考试期间全程考务办驻场，进行现场协调并协助考务工作，各招生单位除选派监考所需的300余名正选监考老师外，同时按1:1配备备用监考老师，以应对人员替换。来自各研究生招生单位的300余名到场监考老师和来自各行政部门的近100名到场专用考场监考老师、考务组织老师及备用老师认真负责，一丝不苟履行监考及考务职责。在全校各单位、部门的通力合作下，共同保障了今年研究生考试的平安有序进行。</p><p><img src=\"https://news.cufe.edu.cn/__local/F/82/07/F70C9794FDB3FF6E757933F7B73_F081D1BC_3382F.png\"/></p><p>▲2023研考工作顺利平稳进行</p><p>在疫情防控背景下，为了给考生营造温暖舒适的考试环境，让考生体会到我校的人文关怀，我校今年实施多项暖心举措服务考生：在两校区的考场门口都设置了抗原检测点，让有需要的考生“安心入校”；制作了考点条幅、路线指引牌和考试信息展板，让考生“快速入校”；为考生提供了热水和热气腾腾的午饭，让考生“暖心备考”；两校区都安排了专用场所供考生中午临时休息使用，让考生“舒适备考”；所有考场都配备了口罩、消毒液等防疫物资并在每场考试结束之后消杀通风，给考生提供一个安全卫生的考场环境，做到“健康研考”。在这个寒冷的冬天，让考生感受到中财大的温馨和温度。</p><p><img src=\"https://news.cufe.edu.cn/__local/F/16/A8/6C7A5159E8EC0CB3352B827D829_84AEB313_2D7C4.jpg\"/></p><p>▲全校各部门通力合作为考生提供全面保障</p><p>为了确保本次研考的顺利进行，学校高度重视，12月9日学校召开了研究生招生考试及考务工作布置会。校长王瑶琪，校党委副书记、副校长马海涛参会。会议要求切实做好研考的考前筹备演练、考中组织管理、考后整理评卷各个环节的工作，学校各部门、各招生学院须落实主体责任，要求学校办公室、宣传部、纪委办公室、监察处、学生处、保卫处、研究生院、智慧校园建设中心、校医院、后勤服务产业集团、中财大资产经营（北京）有限公司等职能部门通力配合，认真做好考试准备工作，共同营造安全、和谐、健康的考试氛围。</p><p><img src=\"https://news.cufe.edu.cn/__local/5/BF/E2/BA342DE061FC30E2EBEA20763E5_82A3823D_7AA22.png\"/></p><p>▲2023年硕士研究生招生考试及考务工作布置会</p><p>为了更好地筹备考务工作，考虑到疫情防控的现实情况，12月21日学校组织召开了线上考务培训会。校党委副书记、副校长马海涛参会。会议对参加此次研招考试的考务人员、监考人员（含正选及备用监考）和纪检监察、疫情防控人员开展了详细全面的考务培训，对今年研考新形势进行研判，对考务人员基本工作要求、学校有关职能部门职责分工等工作进行了培训和布置，并重点强调了监考人员的工作程序与注意事项。纪委办公室、监察处负责人和校医院负责人分别强调了考务人员监考纪律以及疫情防控各项要求。</p><p><img src=\"https://news.cufe.edu.cn/__local/0/EC/6B/34CE2987679B4815C31DF34DDF1_16BFC195_7EAD.jpg\"/></p><p>▲2023年硕士研究生招生考试考务培训会</p><p>同时，学校针对研考工作还制定了详实的防疫工作方案和应急预案，并于12月22日组织了防疫演练，及时发现问题、压实责任。校党委副书记梁勇到场指导，各相关职能部门主要负责人均积极参与、主动承担，群策群力确保研考万无一失。</p><p><img src=\"https://news.cufe.edu.cn/__local/E/40/23/7A57B234CBD4513716B8F9F4704_262C8957_FB30.jpg\"/></p><p>▲防疫演练</p><p>研究生招生考试是深化教育评价改革的重要环节和促进教育公平的重要方式。我校深入贯彻落实全国研究生教育会议精神，根据教育部和北京市的要求，认真组织，严密安排，狠抓落实，切实保障广大考生和考试工作人员的生命安全和身体健康，确保试题试卷的绝对安全和考点考场的秩序井然，落实“一类一策”组考模式要求，全力实现“如期考试、应考尽考、平安研考”工作目标。在全校上下一心的奔赴和坚守下，在各位考生披星戴月的坚持和配合下，圆满完成了2023年全国硕士研究生考试初试组织工作！</p><p>编辑：梁江燕</p>', '2025-03-27 18:27:37');

-- ----------------------------
-- Table structure for system_notice
-- ----------------------------
DROP TABLE IF EXISTS `system_notice`;
CREATE TABLE `system_notice`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(255) NULL DEFAULT NULL COMMENT '发布者',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '公告名称',
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '公告内容',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '公告时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of system_notice
-- ----------------------------
INSERT INTO `system_notice` VALUES (21, 16, '2025年国家公务员考试公告（3.97万）', '<p>　　中央机关及其直属机构2025年度考试录用公务员公告<br/>　　<br/>　　一、报考条件<br/>　　<br/>　　报考者应当具备下列资格条件：<br/>　　<br/>　　（一）具有中华人民共和国国籍；<br/>　　<br/>　　（二）年龄一般为18周岁以上、35周岁以下（1988年10月至2006年10月期间出生），对于2025年应届硕士、博士研究生，放宽到40周岁以下（1983年10月以后出生）；<br/>　　<br/>　　（三）拥护中华人民共和国宪法，拥护中国共产党领导和社会主义制度；<br/>　　<br/>　　（四）具有良好的政治素质和道德品行；<br/>　　<br/>　　（五）具有正常履行职责的身体条件和心理素质；<br/>　　<br/>　　（六）具有符合职位要求的工作能力；<br/>　　<br/>　　（七）具有大学专科及以上文化程度；<br/>　　<br/>　　（八）具备中央公务员主管部门规定的拟任职位所要求的其他资格条件。<br/>　　<br/>　　中央机关及其省级直属机构除部分特殊职位和专业性较强的职位外，主要招录具有2年以上基层工作经历的人员。市（地）级及以下直属机构主要招录应届高校毕业生。基层工作经历，是指在县（市、区、旗）、乡（镇、街道）党政机关，村（社区）党组织或者村（居）委会，以及各类企业、事业单位工作过（参照公务员法管理的事业单位不在此列）。在军队团和相当团以下单位工作的经历，退役士兵在军队服现役的经历，离校未就业高校毕业生到高校毕业生实习见习基地（该基地为基层单位）参加见习或者到企事业单位参与项目研究的经历，可视为基层工作经历。报考中央机关的，曾在市（地、州、盟）直属机关工作的经历，也可视为基层工作经历。直辖市区（县）机关工作经历视同为基层工作经历。基层工作经历计算截止时间为2024年10月。<br/>　　<br/>　　地处艰苦边远地区的县（区）级及以下直属机构，根据《关于做好艰苦边远地区基层公务员考试录用工作的意见》，可以采取调整学历、专业、工作年限和经历条件以及单独划定笔试合格分数线等措施，适当降低进入门槛。对于通过降低进入门槛等倾斜政策录用的人员，应当在所报考市（地、州、盟）辖区内的艰苦边远县乡机关最低服务5年（含试用期）；未满5年的，不得交流（含公开遴选）到本市（地、州、盟）内的上级机关和非艰苦边远地区的机关，也不得交流（含公开遴选）到本省（自治区、直辖市）内其他市（地、州、盟）和其他省（自治区、直辖市）的机关（包括其中艰苦边远地区的机关）。<br/>　　<br/>　　现役军人、在读的非应届高校毕业生、在职公务员和参照公务员法管理的机关（单位）工作人员，不能报考。<br/>　　<br/>　　因犯罪受过刑事处罚的人员、被开除中国共产党党籍的人员、被开除公职的人员、被依法列为失信联合惩戒对象的人员，在各级公务员招考中被认定有舞弊等严重违反录用纪律行为的人员，公务员和参照公务员法管理的机关（单位）工作人员被辞退未满5年的，以及法律法规规定不得录用为公务员的其他情形的人员，不得报考。<br/>　　<br/>　　报考者不得报考录用后即构成公务员法第七十四条第一款所列情形的职位，也不得报考与本人有夫妻关系、直系血亲关系、三代以内旁系血亲关系以及近姻亲关系的人员担任领导成员的用人单位的职位。<br/>　　<br/>　　二、报考程序<br/>　　<br/>　　（一）职位查询<br/>　　<br/>　　各招录机关的招考人数、具体职位、考试类别、资格条件、咨询电话等详见《中央机关及其直属机构2025年度考试录用公务员招考简章》（以下简称《招考简章》），有关报考政策规定、报名网络技术和考场考务安排等事宜详见《中央机关及其直属机构2025年度考试录用公务员报考指南》（以下简称《报考指南》）。《招考简章》、《报考指南》通过中央机关及其直属机构2025年度考试录用公务员专题网站（以下简称“专题网站”，<a href=\"http://bm.scs.gov.cn/kl2025\" target=\"_blank\">http://bm.scs.gov.cn/kl2025</a>）公布。<br/>　　<br/>　　报考者对《招考简章》中有关职位的专业、学历、学位、工作经历等资格条件有疑问需要咨询时，可直接与招录机关联系。<br/>　　<br/>　　（二）网上报名<br/>　　<br/>　　本次招考主要采取网上报名方式，按照以下程序进行：<br/>　　<br/>　　1. 提交报考申请。报考者可于2024年10月15日8:00至10月24日18:00期间登录专题网站进行报名并提交报考申请，每次只能选报1个职位。报名时，应当仔细阅读诚信承诺书，提交的报考申请材料应当真实、准确、完整。报考者提供虚假报考申请材料的，一经查实，将取消报考资格，涉及伪造、变造有关证件、材料、信息骗取考试资格的，将按有关规定严肃处理。<br/>　　<br/>　　2. 查询资格审查结果。招录机关于2024年10月15日8:00至10月26日18:00期间对报考申请进行审查，确认报考者是否具有报考资格。报考者可登录专题网站查询资格审查结果。通过资格审查的，不能再报考其他职位。2024年10月15日8:00至10月24日18:00期间，报考申请未审查或者未通过资格审查的，可以改报其他职位。2024年10月24日18:00以后，报考申请未审查或者未通过资格审查的，不能再改报其他职位。<br/>　　<br/>　　对报考资格的审查贯穿录用全过程。在各环节发现报考者不符合报考资格条件的，均可取消其报考资格或者录用资格。<br/>　　<br/>　　3. 查询报名序号。通过资格审查的报考者，可于2024年10月28日8:00后登录专题网站查询报名序号，报名确认和下载打印准考证时需提供该序号，请务必牢记。<br/>　　<br/>　　（三）报名确认<br/>　　<br/>　　通过资格审查的报考者，可于2024年11月1日0:00至11月6日 24:00登录专题网站进行网上报名确认并缴费。逾期未完成网上报名确认并缴费的，视为自动放弃报考资格。<br/>　　<br/>　　最低生活保障家庭人员可以直接与当地考试机构联系办理报名确认和减免费用手续。各省（自治区、直辖市）考试机构的咨询电话将于2024年10月31日以后通过专题网站公布。<br/>　　<br/>　　（四）网上打印准考证<br/>　　<br/>　　报名确认成功的报考者，请于2024年11月25日0:00至12月1日15:00期间登录专题网站打印准考证。如遇问题，请与当地考试机构联系解决。<br/>　　<br/>　　三、考试内容、时间和地点<br/>　　<br/>　　（一）笔试<br/>　　<br/>　　1. 内容。笔试包括公共科目和专业科目。<br/>　　<br/>　　公共科目包括行政职业能力测验和申论两科。其中，行政职业能力测验为客观性试题，申论为主观性试题，满分均为100分，详见《中央机关及其直属机构2025年度考试录用公务员公共科目笔试考试大纲》。公共科目笔试试卷分为3类，分别适用于中央机关及其省级直属机构综合管理类职位、市（地）级及以下直属机构综合管理类职位和行政执法类职位。所有报考者均需参加公共科目笔试。<br/>　　<br/>　　中共中央对外联络部、中共中央党史和文献研究院、全国友协、中国贸促会、外交部、教育部、科学技术部、商务部、文化和旅游部日语、法语、俄语、西班牙语、阿拉伯语、德语、朝鲜语（韩语）、葡萄牙语等8个非通用语职位的报考者，还需参加统一组织的外语水平测试，考试大纲可在专题网站和外交部网站查询。<br/>　　<br/>　　金融监管总局及其派出机构职位、中国证监会及其派出机构职位，以及公安机关人民警察职位的报考者，还需参加统一组织的专业科目笔试，考试大纲可在专题网站和金融监管总局、中国证监会、公安部网站查询。<br/>　　<br/>　　2. 时间地点。公共科目笔试时间为：<br/>　　<br/>　　2024年12月1日上午  9:00—11:00   行政职业能力测验<br/>　　<br/>　　2024年12月1日下午  14:00—17:00   申论<br/>　　<br/>　　8个非通用语职位外语水平测试，金融监管总局及其派出机构职位、中国证监会及其派出机构职位以及公安机关人民警察职位专业科目笔试时间为：<br/>　　<br/>　　2024年11月30日下午  14:00—16:00<br/>　　<br/>　　笔试在全国各直辖市、省会城市、自治区首府和部分城市设置考点。报考者按照准考证上确定的时间和地点，携带准考证和报名时使用的身份证件等参加考试。金融监管总局及其派出机构职位、中国证监会及其派出机构职位、公安机关人民警察职位以及8个非通用语职位的报考者在网上报名时，务必将考点选择为直辖市、省会城市或者自治区首府。<br/>　　<br/>　　3. 成绩查询。笔试阅卷结束后，由中央公务员主管部门确定各类职位笔试合格分数线，对西部地区和艰苦边远地区职位、基层职位和特殊专业职位等，将予以适当倾斜。<br/>　　<br/>　　报考者可于2025年1月登录专题网站查询笔试成绩和合格分数线，具体时间另行通知。其中，8个非通用语职位，笔试成绩按照行政职业能力测验、申论、外语水平测试成绩（考试成绩均按百分制折算，下同）分别占25%、25%、50%的比例合成；金融监管总局及其派出机构职位和中国证监会及其派出机构职位，笔试成绩按照行政职业能力测验、申论、专业科目笔试成绩分别占25%、25%、50%的比例合成；公安机关人民警察职位，笔试成绩按照行政职业能力测验、申论、专业科目笔试成绩分别占40%、30%、30%的比例合成；其他职位，笔试成绩按照行政职业能力测验、申论成绩各占50%的比例合成。<br/>　　<br/>　　（二）面试<br/>　　<br/>　　中央公务员主管部门根据《招考简章》中规定的面试人数与计划录用人数的比例，按照笔试成绩从高到低的顺序确定各职位进入面试的人选，并在专题网站上公布。达到笔试合格分数线的人数与计划录用人数比例低于规定面试比例的职位，将面向社会进行调剂。调剂在公共科目考试内容相同（即指报考者应答的是同一类试卷）的职位之间进行，调剂公告、职位等通过专题网站发布。<br/>　　<br/>　　招录机关负责面试实施，时间、地点等事项详见招录机关在本部门网站和专题网站上发布的面试公告。面试时，报考者须提供本人身份证件（本人有效居民身份证、学生证、工作证等）原件、所在学校或者单位盖章的报名推荐表、报名登记表等材料。凡有关材料主要信息不实，影响资格审查结果的，招录机关有权取消报考者参加面试的资格。报名推荐表、报名登记表等材料可从专题网站下载、打印。<br/>　　<br/>　　部分招录机关在面试阶段组织专业能力测试，专业能力测试设置情况和测试内容、分值比重等见《招考简章》或者招录机关发布的面试公告。<br/>　　<br/>　　面试结束后，招录机关通过专题网站发布报考者的面试成绩以及综合成绩。其中，未组织专业能力测试的，综合成绩按照笔试、面试成绩各占50%的比例合成；组织专业能力测试的，综合成绩按照笔试成绩占50%、面试成绩和专业能力测试成绩共占50%的比例合成。<br/>　　<br/>　　四、体检和考察<br/>　　<br/>　　招录机关按照综合成绩从高到低的顺序，确定进入体检和考察的人选。个别参加面试人数与计划录用人数比例低于3:1的职位，报考者面试成绩应当达到其所在面试考官小组使用同一套面试题本面试的所有人员的平均分或者招录机关在面试公告中确定的面试合格分数线，方可进入体检和考察。<br/>　　<br/>　　招录机关负责体检和考察实施。体检项目和标准按照《公务员录用体检通用标准（试行）》及操作手册执行。对身体条件有特殊要求的职位，有关体检项目和标准按照《公务员录用体检特殊标准（试行）》执行。涉及个别职位调整体检有关项目标准的，详见《招考简章》或者招录机关发布的面试公告。<br/>　　<br/>　　考察工作按照《公务员录用考察办法（试行）》有关规定进行。招录机关将采取实地走访、个别谈话、审核人事档案（学籍档案）、查询社会信用记录、同本人面谈等方法，了解考察人选政治素质、道德品质、能力素质、心理素质、学习和工作表现、遵纪守法、廉洁自律情况，以及是否具有应当回避的情形，身心健康状况，与招考职位的匹配度等情况。部分中央一级招录机关实行差额考察，实行差额考察的会在面试公告中载明差额比例。<br/>　　<br/>　　根据职位需要，部分招录机关将对报考者有关心理素质进行测评，测评结果作为择优确定拟录用人员的重要参考。公安机关人民警察职位，将对报考者有关体能情况进行测评，体能测评不合格的，不得确定为拟录用人员。<br/>　　<br/>　　五、公示<br/>　　<br/>　　体检和考察结束后，招录机关根据综合成绩、体检结果和考察情况等择优确定拟录用人员，并在本部门网站和专题网站上公示。公示内容包括招录机关名称、拟录用职位、拟录用人员姓名、性别、准考证号、毕业院校、工作单位等，同时公布举报电话，接受社会监督。公示期为5个工作日。<br/>　　<br/>　　六、备案<br/>　　<br/>　　公示期满，对没有问题或者反映的问题不影响录用的人员，办理备案手续；对公示期间反映有影响录用的问题并查实的，不予备案；对反映的问题一时难以查实的，暂缓备案，待查清后再决定是否备案。<br/>　　<br/>　　七、补充录用<br/>　　<br/>　　招考期间出现空缺的职位或者招录机关由于新增用人需求需要及时补充人员的职位，将面向社会统一进行补充录用。补充录用公告、职位等通过专题网站发布。<br/>　　<br/>　　特别提醒：<br/>　　<br/>　　本次招考不出版也不指定考试辅导用书，不举办也不委托任何机构或者个人举办考试辅导培训班。对于社会上有关公务员考试培训、网站或者出版物等，请广大报考者提高警惕、理性对待，避免上当受骗，防止权益受损。请社会各界加强监督，如发现以上情况，请向相关部门举报，依法依规严肃查处。公务员主管部门将会同有关部门，共同维护良好的考试秩序，营造公平公正、安全有序的考试环境。<br/>　　<br/>　　国家公务员局<br/>　　<br/>　　2024年10月<br/></p>', '2025-03-27 18:06:10');
INSERT INTO `system_notice` VALUES (22, 16, '广东省2025年考试录用公务员公告', '<p>根据公务员法和公务员录用规定等法律法规，省委组织部、省人力资源社会保障厅将组织实施2025年全省各级机关（含参照公务员法管理单位，下同）考试录用一级主任科员以下及其他相当职级层次公务员（含参照公务员法管理单位工作人员，下同）工作。现将有关事项公告如下：<br/><br/>　　一、招考职位<br/><br/>　　全省各级机关计划招考公务员17419名。报考者可通过广东组织工作网（<a href=\"https://www.gdzz.gov.cn\" target=\"_blank\">https://www.gdzz.gov.cn/</a>）、广东省人力资源和社会保障厅网（<a href=\"http://hrss.gd.gov.cn\" target=\"_blank\">http://hrss.gd.gov.cn/</a>）、广东人事考试网（<a href=\"http://rsks.gd.gov.cn\" target=\"_blank\">http://rsks.gd.gov.cn/</a>）或“粤省事”“广东发布”微信公众号查阅具体的招考单位、职位、人数和报考资格条件。报考各地级以上市各级机关职位，也可通过登录当地公务员主管部门门户网站进行查阅。<br/><br/>　　二、招考对象<br/><br/>　　招考对象为普通高等院校2025年应届毕业生（非在职）和社会上具有国家承认学历的大学专科以上毕业人员。其中，国内普通高等院校2025年应届毕业生（非在职）须于2025年9月30日以前取得相应毕业证书、学位证书，在境内就读的中外合作办学2025年应届毕业生（非在职）须于2025年12月31日以前取得相应毕业证书、学位证书，其他报考者须于报名首日以前取得相应毕业证书、学位证书。<br/><br/>　　三、报考条件<br/><br/>　　（一）报考资格条件<br/><br/>　　1．具有中华人民共和国国籍，拥护中华人民共和国宪法，拥护中国共产党领导和社会主义制度。<br/><br/>　　2．具有良好的政治素质和道德品行，具有正常履行职责的身体条件、心理素质和符合职位要求的工作能力。<br/><br/>　　3．报考年龄为18至35周岁（即1989年1月8日至2007年1月7日期间出生）。2025年应届硕士、博士研究生以最高学历报考的，报考年龄放宽到40周岁（即1984年1月8日以后出生）。<br/><br/>　　报考法院法官助理、检察院检察官助理职位的年龄为18至35周岁（即1989年1月8日至2007年1月7日期间出生）。<br/><br/>　　报考地级以上市各级公安机关、法院、检察院和监狱、戒毒系统一线管理机构人民警察（司法警察）职位的年龄为18至30周岁（即1994年1月8日至2007年1月7日期间出生），报考此类职位的2025年应届硕士、博士研究生以最高学历报考的，以及报考公安机关法医类和监狱、戒毒系统一线管理机构狱医、心理矫正类等特殊职位的，年龄放宽到35周岁（即1989年1月8日以后出生）。<br/><br/>　　报考公安机关特警类职位的年龄为18至25周岁（即1999年1月8日至2007年1月7日期间出生）。<br/><br/>　　4．具有国家承认的大学专科以上文化程度。非广东省常住户口（生源）报考者限普通高等院校（非在职）本科以上学历并取得学士以上学位的人员（报考“服务基层项目人员和退役大学生士兵”专门职位的除外）。<br/><br/>　　5．具有招录职位要求的其他资格条件：<br/><br/>　　（1）报考要求“具有2年以上基层工作经历”职位，须曾在基层和生产一线工作累计满2年。<br/><br/>　　（2）报考“服务基层项目人员和退役大学生士兵”专门职位的，限报服务（入伍）所在市、本人户籍（生源）所在市或省直机关相关职位；此类人员也可报考符合条件的其他非专门职位。<br/><br/>　　6．法律法规规定的其他条件。<br/><br/>　　（二）不得报考的人员<br/><br/>　　1．在职公务员和参照公务员法管理单位工作人员（含试用期人员）、现役军人、在读的非应届普通高等院校（非在职）毕业生。<br/><br/>　　2．公务员和参照公务员法管理单位工作人员被辞退未满5年的人员。<br/><br/>　　3．曾因犯罪受过刑事处罚、被开除中国共产党党籍、被开除公职、被依法列为失信联合惩戒对象的人员。<br/><br/>　　4．在各级公务员招考中被认定有舞弊等严重违反录用纪律行为的人员。<br/><br/>　　5．法律法规规定不得录用为公务员的其他情形的人员。<br/><br/>　　四、报考程序<br/><br/>　　（一）报名时间<br/><br/>　　2025年1月8日9︰00至14日16︰00。<br/><br/>　　（二）报考注册<br/><br/>　　报考者登录广东省公务员考试录用管理信息系统（<a href=\"https://ggfw.hrss.gd.gov.cn/gwyks/index.do\" target=\"_blank\">https://ggfw.hrss.gd.gov.cn/gwyks/index.do</a>），用本人身份证号码进行注册，在注册前应仔细阅读本公告、全省各级机关招录职位表（附件1）和报考指南（附件2），注册信息应当真实、准确、完整，并对其负完全责任。<br/><br/>　　（三）填报职位<br/><br/>　　报考者注册个人信息后，选择一个职位报名，提交的报考申请材料应当真实、准确、完整。报考者提供虚假报考申请材料的，涉及伪造、变造有关证件、材料、信息骗取考试资格的，将按有关规定严肃处理。<br/><br/>　　网上报名期间，报名系统实时对报考者信息进行初步审核，定期公布各职位报考人数信息。报考者未缴费确认前可更改报考职位，缴费确认后不能再改报其他职位。2025年1月14日16︰00至18︰00期间，只能进行缴费，不可再更改所报职位。<br/><br/>　　报考者不得报考录用后即构成公务员法第七十四条第一款所列情形的职位，也不得报考与本人有夫妻关系、直系血亲关系、三代以内旁系血亲以及近姻亲关系的人员担任领导成员的用人单位的职位。<br/><br/>　　（四）缴费确认<br/><br/>　　通过系统自动实时初步审核的报考者，通过广东公共服务支付平台进行缴费确认。缴费确认时间为2025年1月8日9︰00至14日18︰00。未按期缴费确认的报考者视为自动放弃考试资格。报考者如属城乡最低生活保障对象，可减免考试费，办理减免申请时间统一为2025年1月8日9︰00至14日18︰00。<br/><br/>　　（五）参加考试确认<br/><br/>　　已完成报名缴费的报考者，需于2025年2月24日9︰00至26日16︰00期间，登录报名系统确认是否参加笔试。确认参加的，需签署考试承诺书；确认放弃的，将按原缴费渠道全额退返考试费用；未进行确认的，正常安排考试（后续不再接受退费申请）。<br/><br/>　　（六）下载并打印准考证<br/><br/>　　参加笔试的报考者，请于2025年3月11日9︰00后在报名系统自行下载并打印准考证。准考证是报考者参加公务员考试录用各环节的重要证件，请妥善保管。报考者参加笔试、资格审核、体能测评、面试、体检、心理素质测评时，必须同时携带准考证和本人有效居民身份证（与报名时一致）。<br/><br/>　　（七）报考注意事项<br/><br/>　　1．报考者须按《广东省2025年考试录用公务员专业参考目录》（附件3）进行报考，所学专业应符合报考职位的专业要求，学位种类不等同于报考专业。<br/><br/>　　2．报考者只能选择一个职位进行报名，报名必须使用本人有效居民身份证。<br/><br/>　　3．具有国（境）外学历、学位人员须于报名首日以前取得相应的国（境）外学历学位证书方可报考；在面试资格审核时须出具国家教育部所属相关机构的学历、学位认证函及有关证明材料。<br/><br/>　　4．基层工作经历、服务基层项目期限、退役期限以及相关资历如专业技术资格证书、执业（职业）资格证书、从业资格证书、居民户口簿、期满考核合格证书、退出现役证件等取得时间的计算截止日期为报名首日。<br/><br/>　　5．资格审查贯穿招录工作全过程。在招录各环节发现报考者不符合报考资格条件的，或报考者和有关单位、人员提供的材料信息不实的，取消报考者报考或录用资格。践行诚信报考，对恶意报名、攻击报名系统的人员，将按照有关规定处理。涉嫌犯罪的，移送司法机关处理。<br/><br/>　　五、考试<br/><br/>　　（一）笔试<br/><br/>　　1．笔试科目<br/><br/>　　一般职位为《行政职业能力测验》和《申论》；行政执法类职位为《行政职业能力测验》和《行政执法专业和申论》；公安机关执法勤务职位为《行政职业能力测验》和《公安专业和申论》。笔试大纲见附件4。<br/><br/>　　2．笔试时间<br/><br/>　　2025年3月15日<br/><br/>　　14∶00—15∶30  《行政职业能力测验》<br/><br/>　　16∶15—18∶45  《申论》（含《行政执法专业和申论》《公安专业和申论》）<br/><br/>　　3．笔试成绩计算<br/><br/>　　笔试成绩按四舍五入保留小数点后2位。其中：<br/><br/>　　一般职位笔试成绩=《行政职业能力测验》成绩×50%+《申论》成绩×50%；<br/><br/>　　行政执法类职位笔试成绩=《行政职业能力测验》成绩×40%+《行政执法专业和申论》成绩×60%；<br/><br/>　　公安机关执法勤务职位笔试成绩=《行政职业能力测验》成绩×50%+《公安专业和申论》成绩×50%。<br/><br/>　　4．笔试成绩查询<br/><br/>　　笔试结束后适时公布成绩和合格分数线。报考者可凭身份证号码和准考证号码登录广东省公务员考试录用管理信息系统查询笔试成绩。<br/><br/>　　（二）面试<br/><br/>　　1．确定面试对象<br/><br/>　　同一职位（组）招录人数1—3人的，根据笔试成绩从高分到低分按招录人数1︰3的比例确定面试对象；招录人数4—5人的，根据笔试成绩从高分到低分按招录人数1︰2.5的比例确定面试对象，计算结果出现小数点的向下取整数值，分别为笔试成绩前10名和前12名。笔试合格报考者达不到规定比例的，按实际合格人数确定面试对象。<br/><br/>　　面试对象须按公务员主管部门或招录机关规定的时间、地点进行资格审核和面试。不按规定时间参加资格审核和面试的，视为自动放弃。资格审核不合格的，不得参加面试，招录机关可依次递补面试对象。凡有关材料信息不实，影响资格审核结果的，取消面试资格。<br/><br/>　　公安、法院、检察院及监狱、戒毒系统人民警察（司法警察）职位的报考者，通过资格审核后，还须进行体能测评。体能测评由公务员主管部门会同招录机关组织实施，具体测评时间、地点等事项另行通知。为能有效递补和按时完成体能测评，资格审核和体能测评可在报考者自愿基础上按不低于招录人数1︰4不高于1︰6的比例组织实施。通过资格审核和体能测评、排名在面试名额以外的为面试递补人员。<br/><br/>　　部分招录职位（见职位表）将在面试前后对报考者进行心理素质测评，测评结果作为择优确定拟录用人员的重要参考。<br/><br/>　　2．集中组织面试<br/><br/>　　面试工作严格按照《公务员录用面试组织管理办法（试行）》《广东省公务员录用面试工作实施细则（试行）》等规定执行，全省统一命题，统一时间进行。面试工作另行通知。<br/><br/>　　（三）总成绩计算方法<br/><br/>　　考试总成绩＝笔试成绩×50%＋面试成绩×50%。<br/><br/>　　考试总成绩四舍五入保留小数点后3位。<br/><br/>　　若同一职位报考者总成绩相同，则依次按照面试成绩、《申论》（行政执法类职位为《行政执法专业和申论》，公安机关执法勤务职位为《公安专业和申论》）成绩高低顺序确定名次。<br/><br/>　　六、体检、考察<br/><br/>　　（一）确定人选<br/><br/>　　考试结束后，在考试总成绩合格的报考者中按从高分到低分的顺序，按招录人数等额确定进入体检和考察的人选。<br/><br/>　　报考职位组（包括2个以上不同的具体职位）的报考者，按照考试总成绩从高分到低分的顺序，在所报考职位组中选择一个具体职位（不得挑选录用后即构成回避关系的职位），确定为该职位的体检和考察人选。考试成绩未达合格分数线的，以及笔试、面试任一科目缺考者均不能列为体检、考察人选。<br/><br/>　　（二）组织体检<br/><br/>　　体检工作由各地级以上市公务员主管部门负责组织，招录机关实施。严格按修订后的《公务员录用体检通用标准（试行）》《公务员录用体检操作手册（试行）》《公务员录用体检特殊标准（试行）》《关于进一步做好公务员考试录用体检工作的通知》以及《广东省公务员录用体检工作实施细则（试行）》等规定执行，具体体检事项另行通知。体检人选不按规定的时间、地点参加体检的，视作放弃体检。<br/><br/>　　（三）组织考察<br/><br/>　　考察工作由招录机关负责，按照《公务员录用考察办法（试行）》等有关规定和拟录用职位的要求开展。考察工作突出政治标准，采取实地走访、个别谈话、审核人事档案（学籍档案）、查询社会信用记录、同本人面谈等方式进行，主要了解考察人选的政治素质、道德品行、能力素质、心理素质、学习和工作表现、遵纪守法、廉洁自律、职位匹配度以及是否具有报考回避情形等方面的情况。要对考察人选的个人档案进行严格审核；要注意广泛听取意见，做到全面、客观、公正，对反映的问题要真查细查，要据实写出考察材料。<br/><br/>　　七、公示、录用<br/><br/>　　招录机关按照规定的程序和标准，根据考试成绩、体检结果、考察情况择优确定拟录用人员。<br/><br/>　　拟录用人员名单在招录机关或公务员主管部门门户网站公示。公示内容包括招录机关名称、拟录用职位，拟录用人员姓名、性别、准考证号、毕业院校或工作单位等，同时公布举报电话，接受社会监督。公示期为5个工作日，经公示无反映或反映问题不影响录用的，按规定程序和权限办理录用手续。<br/><br/>　　新录用公务员试用期一年，试用期满考核合格的予以任职定级。<br/><br/>　　八、递补录用<br/><br/>　　有下列情形之一的，可按考试总成绩从高分到低分依次递补体检、考察人选。<br/><br/>　　（一）体检不合格的；<br/><br/>　　（二）体检人选放弃体检的；<br/><br/>　　（三）考察不合格的；<br/><br/>　　（四）考察人选放弃考察的；<br/><br/>　　（五）拟录用人员放弃录用资格的。<br/><br/>　　九、相关事项<br/><br/>　　（一）报名期间开通政策咨询、报名考务咨询、技术服务咨询热线电话，详见报考咨询电话（附件5）。报考者对招考职位的专业、学历、学位等资格条件有疑问需要咨询时，可直接与招录机关或相关考区联系。<br/><br/>　　（二）本公告及其附件的“以上”“以下”“以前”“以后”均包含本级基数。<br/><br/>　　（三）本公告及报考相关要求的附件同时在广东组织工作网、广东省人力资源和社会保障厅网、广东人事考试网发布，并在各地级以上市公务员主管部门门户网站同步转发。本公告由省公务员主管部门负责解释。<br/><br/>　　特别提醒：<br/><br/>　　1．公务员录用考试主要测查从事机关工作应当具备的基本能力素质，这些能力素质主要靠平时学习、工作和生活的长期积累。报考者可仔细阅读《广东省2025年考试录用公务员笔试大纲》，结合职位需求和自身条件，有针对性地进行准备。<br/><br/>　　2．本次招考不委托任何机构或者个人对外发布招考信息，请广大报考者切勿轻易相信任何冠以“内部消息”“内部人士透露”“独家渠道”等有关招考信息，避免上当受骗。<br/><br/>　　3．本次招考不举办、不委托任何机构或者个人举办考试辅导培训班，社会上任何以公务员考试命题组、专门培训机构等名义举办的辅导班、辅导网站或发行的出版物、参考资料等均与本次考试的组织方无关。<br/><br/>　　4．请广大报考者提高警惕，理性对待社会上任何机构或者个人发布含有或者暗示“官方真题”“官方标答”“评分细则”“押题”“命题人员参与”“面试考官参与”等误导性宣传，或者“包过”“保证”等对培训效果作出承诺等行为，避免上当受骗，防止权益受损。请社会各界加强监督，如发现以上情况，可向相关部门举报。　<br/><br/>　　  点击查看：附件 1-5<br/><br/>　　附件：1.广东省2025年考试录用公务员职位表<br/><br/>　　           2.广东省2025年考试录用公务员报考指南<br/><br/>　　           3.广东省2025年考试录用公务员专业参考目录<br/><br/>　　           4.广东省2025年考试录用公务员笔试大纲<br/><br/>　　           5.广东省2025年考试录用公务员报考咨询电话<br/><br/><br/><br/><br/><br/>　　                                                                                        中共广东省委组织部       <br/><br/>　　                                                                                 广东省人力资源和社会保障厅<br/><br/>　　                                                                                   2025年1月7日<br/></p>', '2025-03-27 18:07:36');
INSERT INTO `system_notice` VALUES (23, 16, '2025年保定容城县事业单位招聘考试公告', '<p>　　为满足我县事业单位用人需要，根据《河北省事业单位公开招聘工作人员暂行办法》，容城县事业单位2025年面向社会公开招聘工作人员。现公告如下：<br/><br/>　　一、招聘方式和原则<br/><br/>　　坚持德才兼备，贯彻民主、公开、竞争、择优的原则，实行公开招聘，在考试、考核的基础上择优聘用。<br/><br/>　　二、招聘岗位及应聘条件<br/><br/>　　（一）招聘岗位及名额<br/><br/>　　容城县事业单位工作人员70名；详见《容城县2025年公开招聘事业单位工作人员岗位信息表》。<br/><br/>　　（二）应聘基本条件<br/><br/>　　1.具有中华人民共和国国籍；<br/><br/>　　2.遵守宪法和法律；<br/><br/>　　3.具有良好的品行和职业道德；<br/><br/>　　4.年龄一般在18周岁以上（2007年3月21日前出生），35周岁以下（1989年3月22日后出生），部分岗位年龄可适当放宽（详见《容城县2025年公开招聘事业单位工作人员岗位信息表》）；<br/><br/>　　5.具有与招聘岗位要求相适应的学历、学位、职业资格、专业（对照教育部《普通高等学校本科专业目录（2024年）》（附件2）、《教育部研究生教育学科专业目录（2022年）》（附件3））和技能条件；<br/><br/>　　6.适应岗位要求的身体条件；<br/><br/>　　7.具备岗位所需要的其他条件。<br/><br/>　　留学回国人员和其他海外、外国学历学位获得者的专业名称与招聘岗位要求专业名称不完全一致，但所学专业课程与招聘岗位专业课程大部分相同，且符合该岗位所需要的其他条件的，也可报考该招聘岗位。<br/><br/>　　（三）部分岗位条件的说明<br/><br/>　　1.本次招聘部分岗位用于专项招聘“高校毕业生”，包括以下人员：<br/><br/>　　（1）纳入国家统招计划、被普通高等院校录取的2025年高校毕业生；<br/><br/>　　（2）国家统一招生的2023年、2024年普通高校毕业生在择业期内未落实工作单位，其档案仍保留在原毕业学校，或保留在各级毕业生就业主管部门（毕业生就业指导服务中心）、各级人才交流服务机构和各级公共就业服务机构的毕业生；<br/><br/>　　（3）参加“服务基层项目”前无工作经历，服务期满且考核合格后2年内未落实工作单位的人员；<br/><br/>　　（4）普通高等院校在校生或毕业当年入伍，退役后（含复学毕业）2年内未落实工作单位的退役士兵；<br/><br/>　　（5）2025年取得国（境）外学位并完成教育部门学历认证的留学回国人员，2023年、2024年取得国（境）外学位并完成教育部门学历认证且未落实工作单位的留学回国人员。<br/><br/>　　年龄计算时间截止日期为2025年3月21日。其中，在大学期间的社会实践（实习）经历，不视为工作经历。<br/><br/>　　2.对教师岗位的要求：<br/><br/>　　（1）具有与报考岗位要求相适应的教师资格证，暂未取得教师资格证的人员，在办理聘用手续前须取得相应的教师资格证；<br/><br/>　　（2）报考语文教师岗位普通话水平不低于二级甲等，其他学科教师岗位普通话水平不低于二级乙等（尚未取得普通话证书的应于2025年7月31日前取得）；<br/><br/>　　（3）在校期间必修课成绩无补考记录。<br/><br/>　　（四）本次招聘不受理下列人员报名<br/><br/>　　现役军人、试用期内的公务员和试用期内的事业单位工作人员、未满最低服务年限或未满约定最低服务期限的人员、在读的非应届毕业生。<br/><br/>　　有尚在影响期内的党纪政务处分的人员、正在接受立案审查调查的人员、因犯罪受过刑事处罚的人员、被开除中国共产党党籍的人员、被开除公职的人员、被依法列为失信联合惩戒对象的人员以及法律法规规定不得招聘为事业单位工作人员的其他情形人员。<br/><br/>　　事业单位公开招聘实行回避制度。<br/><br/>　　三、招聘程序和方法<br/><br/>　　本次招聘采取笔试、面试、体检、考核相结合的方法进行，实行网上报名、网上审查、网上缴费。<br/><br/>　　（一）网上报名及资格审核<br/><br/>　　1.报名程序<br/><br/>　　报名网址：<a href=\"http://jyk.wes2.com\" target=\"_blank\">http://jyk.wes2.com/</a><br/><br/>　　报名时间：2025年3月26日9:00至4月1日17:00。<br/><br/>　　缴费时间：2025年3月26日9:00至4月2日10:00。<br/><br/>　　报名基本程序：<br/><br/>　　（1）每人限报一个岗位，多报则取消应聘资格。对于2025年应届毕业生，应以其即将获得的最高学历进行报考；对于其他人员，均应以其已获得的最高学历进行报考。报考人员如实提交有关信息和材料，凡本人填写信息不真实、不完整或填写错误的，责任自负；弄虚作假的，一经查实即取消考试资格或聘用资格。<br/><br/>　　（2）考生“提交审核”后信息将被锁定，在未反馈审核结果前不能修改。事业单位或其主管部门依照发布的招聘条件对报考人员网上报名信息进行资格初审。“审核未过”的，可根据提示的未过原因，修改信息或改报岗位并重新提交审核；“审核通过”的，将不能再修改，可直接进入缴费程序。缴纳报名考务费100元。<br/><br/>　　（3）报名考务费减免对象和办法。报名考务费减免对象包括：建档立卡贫困家庭人员和城乡低保家庭人员。具体流程为：先行报名缴费→提交审核材料→审核通过→退费。建档立卡贫困家庭的考生将本人身份证和当地农业农村局（原乡村振兴局）出具的贫困证明或经当地农业农村局（原乡村振兴局）盖章的扶贫手册原件及复印件，城乡低保家庭的考生将本人身份证和其家庭所在地的县（市、区）民政部门出具的享受最低生活保障的证明或低保证，连同考务费减免申请（含个人姓名、所报岗位、岗位代码及联系方式），于4月7日、4月8日（上午9:00—11:00，下午14:00—17：00）到容城县公共服务局（容城县容美路38号容城县政务服务中心615室）审核并办理减免考试报名考务费用的退费手续。<br/><br/>　　2.如某一岗位的报名人数与招聘计划不足3:1，递减招聘计划至满足比例为止，无法满足的岗位直接取消，并通知应聘人员（限研究生报考岗位不设开考比例）。报考取消岗位的考生可在4月3日上午8:30—11:30内改报一次其他岗位。<br/><br/>　　3.打印《笔试准考证》。缴费成功的考生，请于2025年4月15日9:00后在报名网站打印《笔试准考证》。<br/><br/>　　（二）考试<br/><br/>　　1.笔试<br/><br/>　　（1）笔试时间：2025年4月20日9:00—11:00。具体地点、场次及注意事项见《笔试准考证》。考试开始30分钟后，迟到考生不得再进入考场。考试期间考生不得提前交卷、退场。<br/><br/>　　（2）笔试内容主要包括《公共基础知识》和《职业能力测试》（教师岗位和卫生类岗位分别为相应的专业能力测试），合并一张试卷，满分100分，占总成绩的50%（其中教师岗位笔试成绩占总成绩的40%）。笔试题型全部为客观题，考试时间120分钟。<br/><br/>　　（3）确定笔试最低分数线。笔试结束后，将根据笔试总体情况划定笔试最低控制分数线；对在笔试最低控制分数线以上的考生，根据招聘计划，分岗位，依据笔试成绩由高分到低分，按招聘计划1:3的比例确定进入证件审核考生（达不到1:3比例的按照实际人数进入），比例内末位笔试成绩出现并列的考生，都进入证件审核环节。低于笔试最低控制分数线的考生不得进入后续环节。<br/><br/>　　（4）笔试结束后三日内，考生在报名网站查询笔试成绩及是否进入下一环节。为保护考生个人隐私并参照公务员招录做法，考生只能查询个人成绩。<br/><br/>　　2.证件审核<br/><br/>　　（1）证件审核时间：具体时间、地点另行通知，请关注报名网站证件审核公告。<br/><br/>　　（2）进入证件审核的考生须在规定的时间、地点进行证件审核。考生根据《公告》《岗位信息表》和证件审核公告要求提供相匹配的证书、证件、证明和学信网关于学籍、学历、学位的查询信息（自行打印）等资料的原件及复印件各一份。在职人员须提供原单位同意应聘且加盖单位公章的证明；2025届毕业生须提供学校开具的在校证明（须注明为2025年毕业的统招生）或教育部学籍在线验证报告；应聘“服务基层项目”岗位的考生须出具服务期满合格证书。<br/><br/>　　经审核，符合条件的进入下一环节。如发现在网上报名时填报情况不实，不符合报考条件的考生，取消其进入下一环节资格。因本人放弃或审核未通过造成招聘计划数和进入面试人数比例达不到1:3的，在最低控制分数线上，按笔试成绩从高分到低分依次递补。<br/><br/>　　通过证件审核的考生进入面试环节。<br/><br/>　　3.面试<br/><br/>　　面试时间：面试的具体时间、地点及有关要求等另行通知。<br/><br/>　　（1）综合类和卫生类岗位采取结构化面试方式进行，主要测试应聘人员的综合素质和相关能力，面试时间8分钟；教师岗位采取试讲和教育教学知识问答相结合的形式，备课时间为10分钟，试讲时间不超10分钟，知识问答不超5分钟。面试满分100分，最低合格分数线60分。面试结束后，面试成绩统一公布。<br/><br/>　　（2）面试考生分组。报考同一岗位考生一般在同一个面试组和面试场次。因招聘人数较多无法分在同组、同场的岗位，根据笔试成绩的排名顺序，采取蛇形分组法分组，并将招聘计划合理均分到每个面试小组，按组录取，哪组出现空缺，在哪组顺延递补。<br/><br/>　　（3）面试有关要求。每组考生在入闱后抽签决定面试顺序。面试时考生只报自己的组别和抽签号，不得报姓名。面试结束后立即离开考场，不得返回面试室和候考室，不得在考场附近逗留、议论。对违反规定的，取消面试资格或面试成绩，考生抽签时仍未到候考室视为自动放弃。<br/><br/>　　（4）考试总成绩的合成<br/><br/>　　面试结束后，按笔试成绩、面试成绩两部分计算考试总成绩，总成绩满分100分。<br/><br/>　　教师岗位：总成绩=笔试成绩×40%+面试成绩×60%；<br/><br/>　　其他岗位：总成绩=笔试成绩×50%+面试成绩×50%；<br/><br/>　　笔试、面试成绩均按百分制折算。计算考生成绩时，保留小数点后两位。<br/><br/>　　（5）确定进入体检考生名单。依据考试总成绩从高到低顺序，按岗位招聘人数等额确定进入体检人员名单。比例内末尾总成绩出现并列时，优先进入体检的顺序为《岗位信息表》中有规定的从其规定，其次按以下顺序：①面试成绩较高者；②符合岗位要求学历（学位）层次较高者；③出生日期较晚者。<br/><br/>　　（6）查询考试结果及体检安排。面试结束后，考生及时上网查询考试总成绩及是否进入体检，并按要求做好体检准备。<br/><br/>　　在职人员面试合格后须在报名网站公布面试成绩之日起5个工作日内向容城县公共服务局提供所在单位开具的同意报考证明或离职证明。<br/><br/>　　（三）考核、体检<br/><br/>　　考核、体检具体时间、地点及注意事项在报名网站通知。<br/><br/>　　考核由事业单位或其主管部门组织。主要审核本人档案和相关证明资料，采取多种形式，全面了解被考核对象的政治思想、道德品质、自律意识、业务能力、工作实绩以及需要回避等情况。对其报考条件进行资格复审。<br/><br/>　　体检项目和标准参照《公务员录用体检通用标准（试行）》执行。初次体检不合格人员允许复查一次，复查仍不合格者，取消聘用资格。<br/><br/>　　对考核、体检不合格考生或主动放弃考核、体检的考生，取消其聘用资格，分岗位按考试总成绩从高分到低分进行递补，并对递补考生进行考核、体检。<br/><br/>　　（四）公示<br/><br/>　　根据考试总成绩和体检、考核情况，确定拟聘人选，拟聘人选在容城县政府官网公示7个工作日，接受社会监督。公示期间因各种原因取消拟聘人选聘用资格或拟聘人选放弃聘用的，分岗位按考试总成绩从高分到低分进行递补，并对递补考生进行考核、体检和公示。<br/><br/>　　（五）聘用<br/><br/>　　经公示无异议的拟聘用人员，填写《河北省事业单位公开招聘工作人员审批表》。<br/><br/>　　经审批后，聘用人员要按照程序和要求在规定的时间内报到，对无故不报到者将按《事业单位公开招聘违纪违规行为处理规定》有关规定进行处理。<br/><br/>　　用人单位必须同新聘人员签订《聘用合同》，并为新聘人员办理相关手续。按照有关政策规定实行试用期制度，试用期包括在聘用期内。应聘事业编制岗位人员试用期满经考核合格的，予以正式聘用。考核不合格的，取消聘用。<br/><br/>　　四、其他事宜<br/><br/>　　（一）应聘人员一经正式报名，即视为完全接受本公告的各项规定。报名和考试期间请务必保管好个人的证件和信息，因个人原因造成丢失、被他人盗用及信息被恶意篡改所造成的后果，由考生自负。<br/><br/>　　（二）此次招聘考试违规违纪处理按照《事业单位公开招聘违纪违规行为处理规定》执行。凡考生未在规定时间内按要求参加证件审核、面试、体检、考核、办理聘用手续等情况的，均视为自动放弃应聘资格。资格审核贯穿招聘工作全过程，在任何环节，发现考生不符合招聘条件的，对伪造、假冒各种证件，弄虚作假的，一经查实，取消其应聘资格，问题严重的要追究责任。<br/><br/>　　（三）请考生随时关注容城县政府官网和报名网站，如有时间等信息调整，将提前发布公告，同时请考生保持报名时所留联系电话的畅通，以便通知有关事宜，因所留联系电话无法接通造成的后果自负。<br/><br/>　　（四）本次招聘不指定考试辅导用书，不举办也不委托任何机构举办考试辅导培训班。<br/><br/>　　（五）咨询电话：<br/><br/>　　报名咨询电话：查阅《岗位信息表》（附件1）<br/><br/>　　考务咨询电话：0312—5600304<br/><br/>　　技术咨询电话：0312—5953889<br/><br/>　　监督举报电话：0312—5605025<br/><br/>　　（咨询时间为工作日9:00—11:30，14:30—17:00，仅限考生本人咨询。）<br/><br/>　　五、特别提示<br/><br/>　　本次招聘的政策、方法、程序、时间、地点等有关要求，已在本公告尽可能详细写明，请认真阅读，确有本公告未尽的疑问时再电话咨询，以提高广大考生的咨询效率。<br/><br/>　　请各位考生注意报名和缴费截止时间、打印《笔试准考证》时间、考试时间等重要时间信息，凡是在规定时间未完成相关操作的，将被视为自动放弃。<br/><br/>　　附件：1.容城县2025年公开招聘事业单位工作人员岗位信息表.xlsx (62.9 KB)<br/><br/>　　附件：3.教育部研究生教育学科专业目录（2022年）.pdf (519 KB)<br/><br/>　　附件：2.普通高等学校本科专业目录（2024年）.pdf (817 KB)<br/><br/>　　原始标题：容城县2025年公开招聘事业单位工作人员公告<br/><br/>　　信息来源：<a href=\"https://www.hbrc.gov.cn/news/00-80-67-1-12/3123.html\" target=\"_blank\">https://www.hbrc.gov.cn/news/00-80-67-1-12/3123.html</a><br/><br/>温馨提示：因考试信息、考试政策及招聘内容不断变化与调整，本站提供的以上信息仅供参考，请考生以权威部门发布的公告内容为准。<br/></p>', '2025-03-27 18:09:00');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '密码',
  `key_sm3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码摘要',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '公钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (4, 'teacher', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.IH4U6-UC0R2Fnbr3MyNLZgAAAA?w=158&h=166&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202501162326008002', 'BCXeCZhrck/lNTOqJQwU71RNSt3PGM2rpZOU5XgJ2ccWD8y69XxA6fpum4YFYZlUFvcKSY84Xm3GWK7YKVAIKiRWXfQqy7czsL1B5LwEgg54KEKxCT19B+cQ4Nd/+Z4M0Rk9AU2CMA==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '2563622159@qq.com', '马文', '女', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJrBml1AxU8E7bJKcuz/qQZ3FdK0rsC2OKLNzcbYEscvvwS6WY4M7RVVipRFIUsbSGWEDvAu6dxKccOr3uhWnDw=');
INSERT INTO `teacher` VALUES (5, 'teacher', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.IH4U6-UC0R2Fnbr3MyNLZgAAAA?w=158&h=166&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202503021511459393', 'BJUAC0/NLwmMJpBf0xVJEMTRNxjVJ7ddRWo/Nf2tPvgjOFXBSqORIAe3CiDDtxriKzoLqdQY4FtTtU5mUdx6h2gnaRTW6uAPJKWhU5DEQPrBZMxcSBkeLaqhcP7cG4phE5uehRr5Vw==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '626692428@qq.com', '刘诗慧', '女', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABC3HIf3zrM4G3jpV7HmHmICLiBr8aiXmXu7BC9c3Li6p6JlhiXmsDVWEZUlQJW23tqnpX+bBpFGYpBltfbqQRrE=');
INSERT INTO `teacher` VALUES (6, 'teacher', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.IH4U6-UC0R2Fnbr3MyNLZgAAAA?w=158&h=166&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202503021513120202', 'BGVv6CZblZseYNZinYmnHNxd9vDNoiT8dy0PXiSHFS8jxn2rtXshUFM3jRvMGpwhSBRVwn1F6qLu0MxuiCsUvlZT3mWPYbJ2VoLOFabyQHx1D5RlL8cFxVBlIBwkIi6HGUbJn5DBaQ==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '626692428@qq.com', '李安', '男', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABD+AiFSB1lxxgvt3GEMi0kXXAakhj5utgThLcVBOhn1yb8u1ZnU4oRUAX9y4JL1Y2eSlzO590gvlpqIpiSP7m5M=');
INSERT INTO `teacher` VALUES (7, 'teacher', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.IH4U6-UC0R2Fnbr3MyNLZgAAAA?w=158&h=166&c=7&r=0&o=5&dpr=1.5&pid=1.7', '202503021513502620', 'BFaZkykEJvdUfc7pB530N9+3+brIQrKTKN9NWRZvRIajGzzDOCpYLi6Jk2e/+r97xP1zHBsM4Z5cBPXrFuFOjURNwgSCbAHaK9rSFy7Vi4mYJ/ldIfqBQhdvKHG12uXdK/Rd1C5taw==', '3DE741F445DD357BEA4E6C3FE6437036F62B5E3EC0F00D62796EDB4305ED627F', '939793038@qq.com', '王凯', '男', '19958500349', 'MIIBMzCB7AYHKoZIzj0CATCB4AIBATAsBgcqhkjOPQEBAiEA/////v////////////////////8AAAAA//////////8wRAQg/////v////////////////////8AAAAA//////////wEICjp+p6dn140TVqeS89lCafzl4n1FauPkt28vUFNlA6TBEEEMsSuLB8ZgRlfmQRGajnJlI/jC7/yZgvhcVpFiTNMdMe8Nzai9PZ3nFm9zuNraSFT0KmHfMYqR0AC3zLlITnwoAIhAP////7///////////////9yA99rIcYFK1O79Ak51UEjAgEBA0IABJzSWJ6blwU2vZTbe3XCwEwA6eH8bS+waCo94NPHv++9H1f8jbZT6t0bym3q7lAvocaMPyTkytavNDAg260PPA0=');

SET FOREIGN_KEY_CHECKS = 1;
