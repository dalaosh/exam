"use strict";

const pptxgen = require("pptxgenjs");
const {
  warnIfSlideHasOverlaps,
  warnIfSlideElementsOutOfBounds,
} = require("./pptxgenjs_helpers/layout");

const pptx = new pptxgen();
pptx.layout = "LAYOUT_WIDE";
pptx.author = "OpenAI Codex";
pptx.company = "OpenAI";
pptx.subject = "Examination System Template";
pptx.title = "基于国密算法与人脸认证的考试系统答辩模板";
pptx.lang = "zh-CN";
pptx.theme = {
  headFontFace: "Microsoft YaHei",
  bodyFontFace: "Microsoft YaHei",
  lang: "zh-CN",
};

const C = {
  navy: "10233F",
  teal: "17B7A3",
  tealDark: "0D7F73",
  gold: "F3B54A",
  ink: "1B2430",
  slate: "52606D",
  light: "F5F9FC",
  pale: "E8F2F8",
  white: "FFFFFF",
  line: "D4E0EA",
  blue: "2F80ED",
  green: "33A06F",
  red: "D95555",
};

function addBackground(slide, accent = C.teal) {
  slide.background = { color: C.light };
  slide.addShape(pptx.ShapeType.rect, {
    x: 0,
    y: 0,
    w: 13.333,
    h: 0.42,
    line: { color: C.navy, transparency: 100 },
    fill: { color: C.navy },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 0,
    y: 0.42,
    w: 13.333,
    h: 0.07,
    line: { color: accent, transparency: 100 },
    fill: { color: accent },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 12.86,
    y: 0.49,
    w: 0.473,
    h: 7.01,
    line: { color: C.pale, transparency: 100 },
    fill: { color: C.pale, transparency: 35 },
  });
  slide.addText("Examination System Defense Template", {
    x: 9.2,
    y: 0.11,
    w: 3.25,
    h: 0.16,
    fontFace: "Aptos",
    fontSize: 8,
    color: C.white,
    align: "right",
    margin: 0,
  });
}

function addFooter(slide, pageNo, label = "项目答辩模板") {
  slide.addText(label, {
    x: 0.6,
    y: 7.06,
    w: 2.6,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 8,
    color: C.slate,
    margin: 0,
  });
  slide.addText(String(pageNo).padStart(2, "0"), {
    x: 12.35,
    y: 7.02,
    w: 0.4,
    h: 0.2,
    fontFace: "Aptos",
    fontSize: 10,
    color: C.navy,
    bold: true,
    align: "center",
    margin: 0,
  });
}

function addTitle(slide, title, subtitle) {
  slide.addText(title, {
    x: 0.7,
    y: 0.78,
    w: 7.4,
    h: 0.48,
    fontFace: "Microsoft YaHei",
    fontSize: 22,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText(subtitle, {
    x: 0.72,
    y: 1.28,
    w: 7.1,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 9,
    color: C.tealDark,
    margin: 0,
  });
}

function addSectionBadge(slide, text, x = 11.15, y = 0.82, color = C.gold) {
  slide.addShape(pptx.ShapeType.roundRect, {
    x,
    y,
    w: 1.2,
    h: 0.34,
    rectRadius: 0.07,
    line: { color, transparency: 100 },
    fill: { color },
  });
  slide.addText(text, {
    x: x + 0.08,
    y: y + 0.065,
    w: 1.04,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 9,
    color: C.navy,
    bold: true,
    align: "center",
    margin: 0,
  });
}

function addCard(slide, opts) {
  slide.addShape(pptx.ShapeType.roundRect, {
    x: opts.x,
    y: opts.y,
    w: opts.w,
    h: opts.h,
    rectRadius: 0.06,
    line: { color: opts.lineColor || C.line, pt: 1 },
    fill: { color: opts.fillColor || C.white },
    shadow: {
      type: "outer",
      color: "B8C9D9",
      blur: 1,
      angle: 45,
      distance: 1,
      opacity: 0.12,
    },
  });
}

function addBulletList(slide, items, box, color = C.ink) {
  const runs = [];
  items.forEach((item) => {
    runs.push({
      text: item,
      options: {
        bullet: { indent: 14 },
        breakLine: true,
      },
    });
  });
  slide.addText(runs, {
    x: box.x,
    y: box.y,
    w: box.w,
    h: box.h,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    color,
    margin: 0.04,
    paraSpaceAfterPt: 10,
    valign: "top",
  });
}

function addMetric(slide, x, y, w, value, label, color) {
  addCard(slide, { x, y, w, h: 1.1, lineColor: color, fillColor: C.white });
  slide.addText(value, {
    x: x + 0.18,
    y: y + 0.18,
    w: w - 0.36,
    h: 0.34,
    fontFace: "Aptos",
    fontSize: 20,
    bold: true,
    color,
    align: "center",
    margin: 0,
  });
  slide.addText(label, {
    x: x + 0.16,
    y: y + 0.62,
    w: w - 0.32,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 9,
    color: C.slate,
    align: "center",
    margin: 0,
  });
}

function addFlowStep(slide, x, y, w, h, idx, title, body, color) {
  addCard(slide, { x, y, w, h, lineColor: color, fillColor: C.white });
  slide.addShape(pptx.ShapeType.ellipse, {
    x: x + 0.16,
    y: y + 0.16,
    w: 0.42,
    h: 0.42,
    line: { color, transparency: 100 },
    fill: { color },
  });
  slide.addText(String(idx), {
    x: x + 0.16,
    y: y + 0.235,
    w: 0.42,
    h: 0.14,
    fontFace: "Aptos",
    fontSize: 10,
    bold: true,
    color: C.white,
    align: "center",
    margin: 0,
  });
  slide.addText(title, {
    x: x + 0.7,
    y: y + 0.16,
    w: w - 0.86,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText(body, {
    x: x + 0.16,
    y: y + 0.68,
    w: w - 0.32,
    h: h - 0.82,
    fontFace: "Microsoft YaHei",
    fontSize: 9,
    color: C.slate,
    margin: 0,
    valign: "top",
  });
}

function addArrow(slide, x, y, w, color = C.teal) {
  slide.addShape(pptx.ShapeType.chevron, {
    x,
    y,
    w,
    h: 0.28,
    line: { color, transparency: 100 },
    fill: { color },
  });
}

function finalizeSlide(slide) {
  warnIfSlideHasOverlaps(slide, pptx);
  warnIfSlideElementsOutOfBounds(slide, pptx);
}

function slideCover() {
  const slide = pptx.addSlide();
  slide.background = { color: C.navy };
  slide.addShape(pptx.ShapeType.rect, {
    x: 0,
    y: 0,
    w: 13.333,
    h: 7.5,
    line: { color: C.navy, transparency: 100 },
    fill: { color: C.navy },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 8.3,
    y: 0,
    w: 5.033,
    h: 7.5,
    line: { color: C.teal, transparency: 100 },
    fill: { color: C.teal, transparency: 82 },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 8.8,
    y: 0.55,
    w: 3.5,
    h: 6.4,
    line: { color: C.gold, transparency: 70, pt: 1.2 },
    fill: { color: C.white, transparency: 100 },
  });
  slide.addText("基于国密算法与人脸认证的", {
    x: 0.85,
    y: 1.35,
    w: 5.9,
    h: 0.52,
    fontFace: "Microsoft YaHei",
    fontSize: 24,
    color: C.white,
    margin: 0,
  });
  slide.addText("考试系统答辩模板", {
    x: 0.85,
    y: 1.95,
    w: 6.3,
    h: 0.7,
    fontFace: "Microsoft YaHei",
    fontSize: 28,
    color: C.white,
    bold: true,
    margin: 0,
  });
  slide.addText("可直接替换标题、院校、成员、日期和系统截图", {
    x: 0.9,
    y: 2.84,
    w: 4.4,
    h: 0.28,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: "C9D7E4",
    margin: 0,
  });
  addCard(slide, { x: 0.85, y: 4.15, w: 4.35, h: 1.5, lineColor: C.teal, fillColor: "17304B" });
  slide.addText("项目名称：基于国密算法与人脸认证的考试系统", {
    x: 1.08,
    y: 4.45,
    w: 3.9,
    h: 0.24,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    color: C.white,
    margin: 0,
  });
  slide.addText("汇报人：XXX    指导教师：XXX", {
    x: 1.08,
    y: 4.86,
    w: 3.9,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: "D7E4EF",
    margin: 0,
  });
  slide.addText("学院 / 团队：XXX    日期：2026.XX", {
    x: 1.08,
    y: 5.22,
    w: 3.9,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: "D7E4EF",
    margin: 0,
  });
  slide.addText("01", {
    x: 9.4,
    y: 5.5,
    w: 1.6,
    h: 0.7,
    fontFace: "Aptos",
    fontSize: 34,
    bold: true,
    color: C.white,
    align: "center",
    margin: 0,
  });
  slide.addText("Defense\nTemplate", {
    x: 9.15,
    y: 6.2,
    w: 2.2,
    h: 0.46,
    fontFace: "Aptos",
    fontSize: 12,
    color: "E6F7F4",
    align: "center",
    margin: 0,
  });
  finalizeSlide(slide);
}

function slideAgenda() {
  const slide = pptx.addSlide();
  addBackground(slide);
  addTitle(slide, "目录 / Contents", "保持这一页固定，用于建立整套答辩的章节节奏");
  const items = [
    ["01", "项目概述", "项目定位、目标用户、应用场景"],
    ["02", "项目背景", "行业痛点、建设必要性、问题定义"],
    ["03", "技术路线", "系统架构、关键技术、实现流程"],
    ["04", "主要功能", "多角色业务模块与核心页面演示"],
    ["05", "创新特色", "国密安全、人脸认证、微服务设计"],
    ["06", "成果展望", "部署效果、应用价值、后续计划"],
  ];
  items.forEach((item, index) => {
    const row = Math.floor(index / 2);
    const col = index % 2;
    const x = 0.86 + col * 5.9;
    const y = 1.95 + row * 1.46;
    addCard(slide, { x, y, w: 5.05, h: 1.08, lineColor: C.line, fillColor: C.white });
    slide.addShape(pptx.ShapeType.roundRect, {
      x: x + 0.2,
      y: y + 0.19,
      w: 0.92,
      h: 0.68,
      rectRadius: 0.06,
      line: { color: C.teal, transparency: 100 },
      fill: { color: C.teal },
    });
    slide.addText(item[0], {
      x: x + 0.2,
      y: y + 0.37,
      w: 0.92,
      h: 0.14,
      fontFace: "Aptos",
      fontSize: 14,
      bold: true,
      color: C.white,
      align: "center",
      margin: 0,
    });
    slide.addText(item[1], {
      x: x + 1.32,
      y: y + 0.23,
      w: 1.6,
      h: 0.2,
      fontFace: "Microsoft YaHei",
      fontSize: 13,
      bold: true,
      color: C.ink,
      margin: 0,
    });
    slide.addText(item[2], {
      x: x + 1.34,
      y: y + 0.55,
      w: 3.35,
      h: 0.17,
      fontFace: "Microsoft YaHei",
      fontSize: 8.5,
      color: C.slate,
      margin: 0,
    });
  });
  addFooter(slide, 2);
  finalizeSlide(slide);
}

function slideSection(title, subtitle, no) {
  const slide = pptx.addSlide();
  slide.background = { color: C.light };
  slide.addShape(pptx.ShapeType.rect, {
    x: 0,
    y: 0,
    w: 4.3,
    h: 7.5,
    line: { color: C.navy, transparency: 100 },
    fill: { color: C.navy },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 4.3,
    y: 0,
    w: 9.033,
    h: 7.5,
    line: { color: C.light, transparency: 100 },
    fill: { color: C.light },
  });
  slide.addText(no, {
    x: 0.95,
    y: 1.55,
    w: 2.6,
    h: 0.92,
    fontFace: "Aptos",
    fontSize: 38,
    bold: true,
    color: C.gold,
    margin: 0,
  });
  slide.addText(title, {
    x: 5.0,
    y: 2.1,
    w: 5.7,
    h: 0.62,
    fontFace: "Microsoft YaHei",
    fontSize: 26,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText(subtitle, {
    x: 5.05,
    y: 2.92,
    w: 5.9,
    h: 0.3,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    color: C.tealDark,
    margin: 0,
  });
  slide.addText("章节过渡页，可用于切换到新的部分。", {
    x: 5.05,
    y: 4.08,
    w: 4.4,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: C.slate,
    margin: 0,
  });
  finalizeSlide(slide);
}

function slideOverview() {
  const slide = pptx.addSlide();
  addBackground(slide);
  addTitle(slide, "项目概述", "这页回答“做了什么、给谁用、解决什么问题”");
  addSectionBadge(slide, "OVERVIEW");
  addCard(slide, { x: 0.78, y: 1.82, w: 5.25, h: 4.58, lineColor: C.teal });
  slide.addText("项目定位", {
    x: 1.0,
    y: 2.06,
    w: 1.2,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 14,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText("基于国密算法与人脸认证的考试系统，面向学校与教育机构，覆盖实名认证、组卷、在线考试、阅卷统计与安全审计。", {
    x: 1.0,
    y: 2.42,
    w: 4.65,
    h: 0.95,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    color: C.slate,
    margin: 0,
    valign: "top",
  });
  slide.addText("适用场景", {
    x: 1.0,
    y: 3.8,
    w: 1.3,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 13,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  addBulletList(
    slide,
    ["高校课程考试", "校内资格认证考试", "需要身份验证的线上考试", "多角色协同的考试管理场景"],
    { x: 1.0, y: 4.12, w: 4.45, h: 1.76 }
  );
  addMetric(slide, 6.45, 2.0, 1.8, "4", "核心角色", C.teal);
  addMetric(slide, 8.55, 2.0, 1.8, "7+", "微服务模块", C.blue);
  addMetric(slide, 10.65, 2.0, 1.8, "2", "安全主线", C.gold);
  addCard(slide, { x: 6.45, y: 3.55, w: 5.9, h: 2.85, lineColor: C.line });
  slide.addText("一句话价值", {
    x: 6.7,
    y: 3.85,
    w: 1.5,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 13,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText("通过“国密算法保障数据安全 + 人脸认证确保身份可信 + 微服务支撑业务扩展”，提升线上考试的安全性、公平性和可管理性。", {
    x: 6.7,
    y: 4.26,
    w: 5.25,
    h: 1.25,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    color: C.slate,
    margin: 0,
    valign: "top",
  });
  addFooter(slide, 4);
  finalizeSlide(slide);
}

function slideBackgroundPain() {
  const slide = pptx.addSlide();
  addBackground(slide, C.gold);
  addTitle(slide, "项目背景与痛点", "把需求说清楚，比堆功能更重要");
  addSectionBadge(slide, "PAIN", 11.4, 0.82, C.gold);
  const cards = [
    ["身份真实性难验证", "传统账号密码方式存在替考、冒名登录等风险，缺少可信身份确认手段。", C.red],
    ["考试数据安全要求高", "试题、成绩、密钥等数据涉及保密性、完整性与不可抵赖性，普通方案不足。", C.blue],
    ["系统扩展与协同复杂", "学生、教师、管理员、CA 机构等角色共存，单体架构难以持续扩展。", C.green],
  ];
  cards.forEach((card, index) => {
    const x = 0.9 + index * 4.15;
    addCard(slide, { x, y: 2.02, w: 3.55, h: 3.25, lineColor: card[2] });
    slide.addShape(pptx.ShapeType.ellipse, {
      x: x + 0.2,
      y: 2.22,
      w: 0.48,
      h: 0.48,
      line: { color: card[2], transparency: 100 },
      fill: { color: card[2] },
    });
    slide.addText(String(index + 1), {
      x: x + 0.2,
      y: 2.32,
      w: 0.48,
      h: 0.13,
      fontFace: "Aptos",
      fontSize: 10,
      bold: true,
      color: C.white,
      align: "center",
      margin: 0,
    });
    slide.addText(card[0], {
      x: x + 0.82,
      y: 2.24,
      w: 2.45,
      h: 0.22,
      fontFace: "Microsoft YaHei",
      fontSize: 12,
      bold: true,
      color: C.ink,
      margin: 0,
    });
    slide.addText(card[1], {
      x: x + 0.22,
      y: 2.92,
      w: 3.08,
      h: 1.32,
      fontFace: "Microsoft YaHei",
      fontSize: 10,
      color: C.slate,
      margin: 0,
      valign: "top",
    });
    slide.addText("可替换为政策背景、行业案例或学校实际需求。", {
      x: x + 0.22,
      y: 4.62,
      w: 3.02,
      h: 0.18,
      fontFace: "Microsoft YaHei",
      fontSize: 8.5,
      color: card[2],
      italic: true,
      margin: 0,
    });
  });
  addCard(slide, { x: 0.9, y: 5.6, w: 11.4, h: 0.82, lineColor: C.line, fillColor: "FCFEFF" });
  slide.addText("结论：项目建设的核心不是“把考试搬到线上”，而是构建一套身份可信、数据安全、流程可控、结果可追溯的考试平台。", {
    x: 1.12,
    y: 5.89,
    w: 10.95,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.ink,
    align: "center",
    margin: 0,
  });
  addFooter(slide, 5);
  finalizeSlide(slide);
}

function slideArchitecture() {
  const slide = pptx.addSlide();
  addBackground(slide);
  addTitle(slide, "系统总体架构", "推荐直接放一张总架构图，这页是答辩时最重要的技术页之一");
  addSectionBadge(slide, "ARCH");
  addCard(slide, { x: 0.82, y: 1.82, w: 11.55, h: 4.95, lineColor: C.line });
  slide.addText("前端展示层", {
    x: 1.1,
    y: 2.08,
    w: 1.5,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.tealDark,
    margin: 0,
  });
  const front = [
    ["门户首页", 1.0],
    ["学生端", 2.62],
    ["教师端", 4.24],
    ["管理员 / CA", 5.86],
    ["可视化大屏", 7.48],
  ];
  front.forEach(([label, x]) => {
    addCard(slide, { x, y: 2.42, w: 1.35, h: 0.78, lineColor: C.teal, fillColor: "F4FCFB" });
    slide.addText(label, {
      x: x + 0.1,
      y: 2.7,
      w: 1.15,
      h: 0.14,
      fontFace: "Microsoft YaHei",
      fontSize: 9.5,
      color: C.ink,
      bold: true,
      align: "center",
      margin: 0,
    });
  });
  addArrow(slide, 9.15, 2.67, 0.46);
  addCard(slide, { x: 9.72, y: 2.42, w: 2.0, h: 0.78, lineColor: C.gold, fillColor: "FFF9ED" });
  slide.addText("API Gateway", {
    x: 9.9,
    y: 2.7,
    w: 1.65,
    h: 0.14,
    fontFace: "Aptos",
    fontSize: 11,
    color: C.ink,
    bold: true,
    align: "center",
    margin: 0,
  });
  slide.addText("业务微服务层", {
    x: 1.1,
    y: 3.62,
    w: 1.8,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.tealDark,
    margin: 0,
  });
  const services = [
    "用户服务",
    "考试服务",
    "文件服务",
    "统计服务",
    "人脸认证服务",
    "邮件服务",
    "WebSocket 服务",
  ];
  services.forEach((label, index) => {
    const x = 1.0 + (index % 4) * 2.55;
    const y = 3.95 + Math.floor(index / 4) * 0.94;
    addCard(slide, { x, y, w: 2.0, h: 0.62, lineColor: C.line, fillColor: C.white });
    slide.addText(label, {
      x: x + 0.1,
      y: y + 0.22,
      w: 1.8,
      h: 0.12,
      fontFace: "Microsoft YaHei",
      fontSize: 9.5,
      color: C.ink,
      align: "center",
      margin: 0,
    });
  });
  slide.addText("基础设施层", {
    x: 1.1,
    y: 5.96,
    w: 1.5,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.tealDark,
    margin: 0,
  });
  const infra = [
    ["MySQL", 1.0, C.blue],
    ["Redis", 3.0, C.red],
    ["ArcFace SDK", 5.0, C.gold],
    ["国密算法组件", 7.2, C.green],
    ["部署环境 / 日志", 9.45, C.teal],
  ];
  infra.forEach((item) => {
    addCard(slide, { x: item[1], y: 6.22, w: 1.62, h: 0.54, lineColor: item[2], fillColor: C.white });
    slide.addText(item[0], {
      x: item[1] + 0.08,
      y: 6.42,
      w: 1.46,
      h: 0.11,
      fontFace: "Aptos",
      fontSize: 9,
      color: C.ink,
      bold: true,
      align: "center",
      margin: 0,
    });
  });
  addFooter(slide, 6);
  finalizeSlide(slide);
}

function slideTechRoute() {
  const slide = pptx.addSlide();
  addBackground(slide, C.blue);
  addTitle(slide, "技术路线", "建议按“前端交互 -> 网关转发 -> 服务处理 -> 存储与认证”讲解");
  addSectionBadge(slide, "TECH", 11.3, 0.82, C.blue);
  const steps = [
    ["用户请求", "学生/教师/管理员从 Web 前端访问系统页面并发起业务请求。", C.teal],
    ["网关路由", "统一鉴权、转发与服务聚合，屏蔽后端服务拆分细节。", C.gold],
    ["业务处理", "考试、用户、统计、文件、人脸等服务独立处理并协同。", C.blue],
    ["安全保障", "通过国密算法与人脸认证形成双重安全能力。", C.green],
  ];
  steps.forEach((item, index) => {
    addFlowStep(slide, 0.92 + index * 3.05, 2.15, 2.42, 2.58, index + 1, item[0], item[1], item[2]);
    if (index < steps.length - 1) {
      addArrow(slide, 3.44 + index * 3.05, 3.28, 0.24, item[2]);
    }
  });
  addCard(slide, { x: 1.0, y: 5.15, w: 11.15, h: 1.15, lineColor: C.line });
  slide.addText("可替换内容建议：", {
    x: 1.22,
    y: 5.45,
    w: 1.4,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 11,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText("把当前项目中的真实技术点写进去，例如 Vue2、ElementUI、Spring Cloud Alibaba、MySQL、Redis、SM2/SM3/SM4、ArcFace。", {
    x: 2.78,
    y: 5.43,
    w: 8.77,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: C.slate,
    margin: 0,
  });
  addFooter(slide, 7);
  finalizeSlide(slide);
}

function slideRoleModules() {
  const slide = pptx.addSlide();
  addBackground(slide);
  addTitle(slide, "多角色业务模块", "这页适合概览全部端口和核心模块，后面再分角色拆开");
  addSectionBadge(slide, "ROLES");
  const roles = [
    ["学生端", ["实名注册", "选课/课程", "在线考试", "成绩查询"], C.teal],
    ["教师端", ["课程管理", "题库管理", "组卷发卷", "阅卷统计"], C.blue],
    ["管理员端", ["用户维护", "公告新闻", "系统日志", "密钥管理"], C.gold],
    ["CA 机构端", ["人员审核", "机构信息", "人脸认证复核", "国密体系支撑"], C.green],
  ];
  roles.forEach((role, index) => {
    const x = 0.95 + (index % 2) * 5.9;
    const y = 1.95 + Math.floor(index / 2) * 2.15;
    addCard(slide, { x, y, w: 5.08, h: 1.68, lineColor: role[2] });
    slide.addShape(pptx.ShapeType.rect, {
      x,
      y,
      w: 0.16,
      h: 1.68,
      line: { color: role[2], transparency: 100 },
      fill: { color: role[2] },
    });
    slide.addText(role[0], {
      x: x + 0.32,
      y: y + 0.18,
      w: 1.5,
      h: 0.2,
      fontFace: "Microsoft YaHei",
      fontSize: 13,
      bold: true,
      color: C.ink,
      margin: 0,
    });
    role[1].forEach((item, itemIndex) => {
      slide.addText(item, {
        x: x + 0.36 + (itemIndex % 2) * 1.92,
        y: y + 0.62 + Math.floor(itemIndex / 2) * 0.38,
        w: 1.55,
        h: 0.12,
        fontFace: "Microsoft YaHei",
        fontSize: 10,
        color: C.slate,
        margin: 0,
      });
    });
  });
  addCard(slide, { x: 0.95, y: 6.25, w: 11.1, h: 0.45, lineColor: C.line, fillColor: "FCFEFF" });
  slide.addText("可在每个卡片右侧插入系统页面截图，形成“功能点 + 页面”双证据展示。", {
    x: 1.2,
    y: 6.4,
    w: 10.65,
    h: 0.12,
    fontFace: "Microsoft YaHei",
    fontSize: 9.5,
    color: C.slate,
    align: "center",
    margin: 0,
  });
  addFooter(slide, 8);
  finalizeSlide(slide);
}

function slideStudentTeacher() {
  const slide = pptx.addSlide();
  addBackground(slide, C.teal);
  addTitle(slide, "核心功能页模板", "此页可复制两到三次，分别替换成学生端、教师端、管理员端功能展示");
  addSectionBadge(slide, "DEMO");
  addCard(slide, { x: 0.9, y: 1.95, w: 6.1, h: 4.75, lineColor: C.line });
  slide.addText("页面截图占位区", {
    x: 2.75,
    y: 4.1,
    w: 2.3,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 15,
    bold: true,
    color: C.slate,
    align: "center",
    margin: 0,
  });
  slide.addText("建议替换为课程管理、试卷管理、考试列表等真实截图", {
    x: 1.55,
    y: 4.46,
    w: 4.8,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 9.5,
    color: C.tealDark,
    align: "center",
    margin: 0,
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 1.14,
    y: 2.22,
    w: 5.62,
    h: 3.86,
    line: { color: C.line, dash: "dash", pt: 1.2 },
    fill: { color: C.white },
  });
  addCard(slide, { x: 7.35, y: 1.95, w: 4.55, h: 4.75, lineColor: C.teal });
  slide.addText("功能讲解模板", {
    x: 7.64,
    y: 2.18,
    w: 1.6,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 14,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  addBulletList(
    slide,
    ["功能 1：用一句话解释业务目标", "功能 2：补充用户操作流程", "功能 3：强调系统约束或安全设计", "功能 4：说明带来的管理价值"],
    { x: 7.58, y: 2.68, w: 3.85, h: 2.1 }
  );
  slide.addText("答辩建议", {
    x: 7.64,
    y: 5.18,
    w: 1.2,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 12,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText("不要泛泛而谈，最好按“进入页面 -> 执行动作 -> 产生结果”的顺序说明。", {
    x: 7.64,
    y: 5.52,
    w: 3.82,
    h: 0.6,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: C.slate,
    margin: 0,
    valign: "top",
  });
  addFooter(slide, 9);
  finalizeSlide(slide);
}

function slideFaceFlow() {
  const slide = pptx.addSlide();
  addBackground(slide, C.gold);
  addTitle(slide, "人脸认证流程", "建议把这一页做成项目亮点页，重点讲实名认证与考前验证");
  addSectionBadge(slide, "FACE", 11.35, 0.82, C.gold);
  const flow = [
    ["学生实名注册", "采集身份信息与人脸照片，提交实名认证申请。"],
    ["人脸特征提取", "调用 ArcFace 进行检测、提取特征并保存照片路径。"],
    ["人脸库入库", "将 faceId、faceFeature、facePath 等数据保存到系统。"],
    ["考前再次识别", "考试前拍照比对，返回相似度与匹配结果。"],
    ["人工复核", "教师或 CA 机构对异常情况进行代理确认与审核。"],
  ];
  flow.forEach((item, index) => {
    addFlowStep(slide, 0.86 + index * 2.45, 2.25, 2.0, 2.82, index + 1, item[0], item[1], [C.teal, C.blue, C.green, C.gold, C.red][index]);
    if (index < flow.length - 1) {
      addArrow(slide, 2.94 + index * 2.45, 3.48, 0.16, C.line);
    }
  });
  addCard(slide, { x: 0.95, y: 5.55, w: 11.15, h: 0.92, lineColor: C.line });
  slide.addText("展示建议：左侧放流程图，右下角可补充接口名 `faceAdd`、`faceSearch`，再配一张学生端认证页截图。", {
    x: 1.22,
    y: 5.89,
    w: 10.55,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: C.slate,
    align: "center",
    margin: 0,
  });
  addFooter(slide, 10);
  finalizeSlide(slide);
}

function slideCrypto() {
  const slide = pptx.addSlide();
  addBackground(slide, C.green);
  addTitle(slide, "国密安全机制", "安全页不要空泛，建议按“对象、手段、作用”拆开讲");
  addSectionBadge(slide, "SM", 11.55, 0.82, C.green);
  const security = [
    ["数据机密性", "对敏感考试数据进行加密存储与传输保护。", "SM4 / 加密传输"],
    ["完整性校验", "通过摘要和签名机制防止试题、成绩被篡改。", "SM3 / SM2"],
    ["身份可信", "结合人脸认证与角色权限控制，确保操作主体可信。", "认证 + 授权"],
  ];
  security.forEach((item, index) => {
    const y = 2.02 + index * 1.38;
    addCard(slide, { x: 0.95, y, w: 11.1, h: 1.0, lineColor: C.line });
    slide.addShape(pptx.ShapeType.roundRect, {
      x: 1.18,
      y: y + 0.22,
      w: 1.4,
      h: 0.54,
      rectRadius: 0.06,
      line: { color: C.green, transparency: 100 },
      fill: { color: "EFFAF4" },
    });
    slide.addText(item[0], {
      x: 1.3,
      y: y + 0.41,
      w: 1.15,
      h: 0.12,
      fontFace: "Microsoft YaHei",
      fontSize: 10,
      bold: true,
      color: C.green,
      align: "center",
      margin: 0,
    });
    slide.addText(item[1], {
      x: 2.95,
      y: y + 0.29,
      w: 5.8,
      h: 0.17,
      fontFace: "Microsoft YaHei",
      fontSize: 10.5,
      color: C.ink,
      margin: 0,
    });
    slide.addText(item[2], {
      x: 9.45,
      y: y + 0.29,
      w: 1.35,
      h: 0.17,
      fontFace: "Aptos",
      fontSize: 10,
      bold: true,
      color: C.green,
      align: "center",
      margin: 0,
    });
  });
  addCard(slide, { x: 0.95, y: 6.28, w: 11.1, h: 0.42, lineColor: C.line, fillColor: "FCFEFF" });
  slide.addText("可替换为你项目中的真实密钥体系、公钥展示、验签流程截图。", {
    x: 1.16,
    y: 6.42,
    w: 10.7,
    h: 0.12,
    fontFace: "Microsoft YaHei",
    fontSize: 9.5,
    color: C.slate,
    align: "center",
    margin: 0,
  });
  addFooter(slide, 11);
  finalizeSlide(slide);
}

function slideBigScreen() {
  const slide = pptx.addSlide();
  addBackground(slide, C.blue);
  addTitle(slide, "可视化与统计分析", "大屏和统计页适合突出“系统不仅能用，还能辅助管理决策”");
  addSectionBadge(slide, "DATA", 11.3, 0.82, C.blue);
  addCard(slide, { x: 0.92, y: 1.92, w: 7.1, h: 4.75, lineColor: C.line });
  slide.addShape(pptx.ShapeType.rect, {
    x: 1.15,
    y: 2.18,
    w: 6.64,
    h: 4.18,
    line: { color: C.line, dash: "dash", pt: 1.2 },
    fill: { color: "F8FBFE" },
  });
  slide.addText("大屏 / 图表占位区", {
    x: 3.0,
    y: 4.02,
    w: 2.9,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 16,
    bold: true,
    color: C.slate,
    align: "center",
    margin: 0,
  });
  addCard(slide, { x: 8.35, y: 1.92, w: 3.65, h: 1.22, lineColor: C.teal });
  addCard(slide, { x: 8.35, y: 3.35, w: 3.65, h: 1.22, lineColor: C.gold });
  addCard(slide, { x: 8.35, y: 4.78, w: 3.65, h: 1.22, lineColor: C.green });
  const stats = [
    ["统计维度 1", "考试数量、参考人数、通过率等"],
    ["统计维度 2", "成绩分布、题目分析、课程维度对比"],
    ["统计维度 3", "人脸认证情况、异常行为、日志审计"],
  ];
  stats.forEach((item, index) => {
    const y = 2.12 + index * 1.43;
    slide.addText(item[0], {
      x: 8.62,
      y: y + 0.02,
      w: 1.3,
      h: 0.16,
      fontFace: "Microsoft YaHei",
      fontSize: 12,
      bold: true,
      color: C.ink,
      margin: 0,
    });
    slide.addText(item[1], {
      x: 8.62,
      y: y + 0.38,
      w: 2.95,
      h: 0.34,
      fontFace: "Microsoft YaHei",
      fontSize: 9.5,
      color: C.slate,
      margin: 0,
    });
  });
  addFooter(slide, 12);
  finalizeSlide(slide);
}

function slideInnovation() {
  const slide = pptx.addSlide();
  addBackground(slide, C.gold);
  addTitle(slide, "创新点与特色", "控制在 3 到 4 条最有效，不要写成泛泛口号");
  addSectionBadge(slide, "INNO", 11.25, 0.82, C.gold);
  const innovations = [
    ["双重安全主线", "国密算法保障数据安全，人脸认证保障身份可信。", C.teal],
    ["多角色协同", "学生、教师、管理员、CA 机构围绕考试全流程协同。", C.blue],
    ["微服务可扩展", "服务拆分清晰，便于后续接入更多业务能力。", C.green],
    ["可视化辅助管理", "借助统计分析与大屏展示提升管理效率。", C.red],
  ];
  innovations.forEach((item, index) => {
    const x = 0.98 + (index % 2) * 5.62;
    const y = 2.02 + Math.floor(index / 2) * 2.02;
    addCard(slide, { x, y, w: 4.9, h: 1.48, lineColor: item[2] });
    slide.addText(`0${index + 1}`, {
      x: x + 0.22,
      y: y + 0.23,
      w: 0.52,
      h: 0.16,
      fontFace: "Aptos",
      fontSize: 14,
      bold: true,
      color: item[2],
      margin: 0,
    });
    slide.addText(item[0], {
      x: x + 0.9,
      y: y + 0.22,
      w: 2.0,
      h: 0.18,
      fontFace: "Microsoft YaHei",
      fontSize: 12,
      bold: true,
      color: C.ink,
      margin: 0,
    });
    slide.addText(item[1], {
      x: x + 0.22,
      y: y + 0.66,
      w: 4.3,
      h: 0.4,
      fontFace: "Microsoft YaHei",
      fontSize: 10,
      color: C.slate,
      margin: 0,
    });
  });
  addFooter(slide, 13);
  finalizeSlide(slide);
}

function slideDeployment() {
  const slide = pptx.addSlide();
  addBackground(slide);
  addTitle(slide, "部署与运行效果", "适合放本机部署截图、端口说明和功能验证结果");
  addSectionBadge(slide, "DEPLOY");
  addCard(slide, { x: 0.92, y: 1.98, w: 4.2, h: 4.55, lineColor: C.line });
  slide.addText("部署环境模板", {
    x: 1.18,
    y: 2.24,
    w: 1.5,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 13,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  addBulletList(
    slide,
    ["操作系统：Windows / Linux", "前端：Vue 项目启动端口", "网关：统一入口端口", "后端：各微服务独立端口", "数据库：MySQL + Redis"],
    { x: 1.12, y: 2.66, w: 3.62, h: 2.6 }
  );
  addCard(slide, { x: 5.45, y: 1.98, w: 6.58, h: 2.18, lineColor: C.teal });
  addCard(slide, { x: 5.45, y: 4.35, w: 6.58, h: 2.18, lineColor: C.gold });
  slide.addText("功能验证区", {
    x: 5.76,
    y: 2.26,
    w: 1.3,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 13,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addText("可填写：教师端登录成功、学生端登录成功、人脸认证接口可用、首页统计正常加载等。", {
    x: 5.78,
    y: 2.7,
    w: 5.65,
    h: 0.5,
    fontFace: "Microsoft YaHei",
    fontSize: 10,
    color: C.slate,
    margin: 0,
  });
  slide.addText("截图占位区", {
    x: 5.76,
    y: 4.64,
    w: 1.4,
    h: 0.18,
    fontFace: "Microsoft YaHei",
    fontSize: 13,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 7.45,
    y: 4.64,
    w: 3.75,
    h: 1.44,
    line: { color: C.line, dash: "dash", pt: 1.2 },
    fill: { color: "F8FBFE" },
  });
  addFooter(slide, 14);
  finalizeSlide(slide);
}

function slideValueOutlook() {
  const slide = pptx.addSlide();
  addBackground(slide, C.green);
  addTitle(slide, "应用价值与前景展望", "建议从“当前价值 + 后续可扩展方向”两个层面收束全篇");
  addSectionBadge(slide, "VALUE", 11.2, 0.82, C.green);
  addCard(slide, { x: 0.92, y: 1.96, w: 5.2, h: 4.7, lineColor: C.line });
  addCard(slide, { x: 6.38, y: 1.96, w: 5.7, h: 4.7, lineColor: C.line });
  slide.addText("当前价值", {
    x: 1.18,
    y: 2.22,
    w: 1.2,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 14,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  addBulletList(
    slide,
    ["提升线上考试身份认证可信度", "增强试题与成绩数据安全性", "提高教师和管理者的工作效率", "支持多角色协同和系统持续扩展"],
    { x: 1.14, y: 2.62, w: 4.35, h: 2.2 }
  );
  slide.addText("前景展望", {
    x: 6.66,
    y: 2.22,
    w: 1.4,
    h: 0.2,
    fontFace: "Microsoft YaHei",
    fontSize: 14,
    bold: true,
    color: C.ink,
    margin: 0,
  });
  addBulletList(
    slide,
    ["引入活体检测与异常行为识别", "扩展移动端或小程序端能力", "接入更多学校/考试场景", "完善日志审计和安全追踪体系"],
    { x: 6.62, y: 2.62, w: 4.58, h: 2.2 }
  );
  addFooter(slide, 15);
  finalizeSlide(slide);
}

function slideEnding() {
  const slide = pptx.addSlide();
  slide.background = { color: C.navy };
  slide.addShape(pptx.ShapeType.rect, {
    x: 0,
    y: 0,
    w: 13.333,
    h: 7.5,
    line: { color: C.navy, transparency: 100 },
    fill: { color: C.navy },
  });
  slide.addShape(pptx.ShapeType.rect, {
    x: 0.85,
    y: 0.78,
    w: 11.6,
    h: 5.94,
    line: { color: C.teal, pt: 1.4, transparency: 30 },
    fill: { color: C.white, transparency: 100 },
  });
  slide.addText("THANK YOU", {
    x: 3.6,
    y: 2.0,
    w: 6.1,
    h: 0.7,
    fontFace: "Aptos",
    fontSize: 28,
    bold: true,
    color: C.gold,
    align: "center",
    margin: 0,
  });
  slide.addText("感谢聆听", {
    x: 4.1,
    y: 2.92,
    w: 5.1,
    h: 0.46,
    fontFace: "Microsoft YaHei",
    fontSize: 24,
    bold: true,
    color: C.white,
    align: "center",
    margin: 0,
  });
  slide.addText("这一页可替换为“答辩结束 / 敬请指正 / Q&A”。", {
    x: 3.1,
    y: 3.72,
    w: 7.1,
    h: 0.22,
    fontFace: "Microsoft YaHei",
    fontSize: 10.5,
    color: "D9E4EF",
    align: "center",
    margin: 0,
  });
  slide.addText("基于国密算法与人脸认证的考试系统", {
    x: 3.4,
    y: 5.45,
    w: 6.45,
    h: 0.24,
    fontFace: "Microsoft YaHei",
    fontSize: 12,
    color: C.teal,
    align: "center",
    margin: 0,
  });
  finalizeSlide(slide);
}

slideCover();
slideAgenda();
slideSection("项目概述", "Project Overview", "01");
slideOverview();
slideBackgroundPain();
slideArchitecture();
slideTechRoute();
slideRoleModules();
slideStudentTeacher();
slideFaceFlow();
slideCrypto();
slideBigScreen();
slideInnovation();
slideDeployment();
slideValueOutlook();
slideEnding();

pptx.writeFile({ fileName: "基于国密算法与人脸认证的考试系统_答辩模板.pptx" });
