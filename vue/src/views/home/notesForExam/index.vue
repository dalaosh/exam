<template>
  <div class="backgrounds">
    <!-- 考试注意事项标题 -->
    <div style="text-align: center; line-height: 10vh; background-color: rgba(232, 232, 229, 0.5);">
      <strong style="font-size: 30px; margin-left: 5px; color: #193624">考试注意事项 | Examination Notes</strong>
    </div>
    <!-- 考试注意事项内容 -->
    <div style="background-color: rgba(87, 103, 101, 0.3); padding: 20px;">
      <ol style="padding-left: 20px; font-size: 18px; line-height: 1.6;">
        <li>
          <strong>身份验证环节 | Identity Verification</strong>
          <ul>
            <li>请在光线充足、无遮挡的环境下进行虹软人脸识别身份验证。避免佩戴帽子、眼镜（特别是墨镜）、口罩等可能影响识别效果的物品。
              <br>Please conduct the ArcSoft face recognition identity verification in a well - lit and unobstructed environment. Avoid wearing items such as hats, glasses (especially sunglasses), and masks that may affect the recognition effect.</li>
            <li>保持面部表情自然，正视摄像头，确保脸部完整地出现在识别框内。若多次识别失败，请检查网络连接或联系监考人员协助解决。
              <br>Keep a natural facial expression, look straight into the camera, and ensure that your face is fully within the recognition frame. If the recognition fails multiple times, please check your network connection or contact the invigilator for assistance.</li>
          </ul>
        </li>
        <li>
          <strong>考试环境要求 | Examination Environment Requirements</strong>
          <ul>
            <li>选择安静、独立且无干扰的空间进行考试，避免他人进入考试区域或有嘈杂声音影响考试。
              <br>Choose a quiet, independent, and undisturbed space for the exam. Avoid having others enter the exam area or noisy sounds that may affect the exam.</li>
            <li>确保考试设备电量充足，并连接稳定的网络。建议提前进行网络测试，以防止考试过程中出现网络中断的情况。
              <br>Ensure that your exam device has sufficient battery power and is connected to a stable network. It is recommended to conduct a network test in advance to prevent network interruptions during the exam.</li>
          </ul>
        </li>
        <li>
          <strong>C 语言编程答题须知 | Instructions for C Language Programming Questions</strong>
          <ul>
            <li>在 C 语言代码编程运行子系统中答题时，要仔细阅读题目要求，按照规范编写代码。注意代码的语法正确性和逻辑合理性。
              <br>When answering questions in the C language code programming and running subsystem, carefully read the question requirements and write the code according to the specifications. Pay attention to the correctness of the code syntax and the rationality of the logic.</li>
            <li>提交代码前，务必对代码进行充分的测试，确保代码能够正常编译和运行，并得到预期的结果。
              <br>Before submitting the code, be sure to thoroughly test the code to ensure that it can be compiled and run normally and achieve the expected results.</li>
            <li>如果遇到编译错误或运行异常，不要慌张，仔细检查代码，逐步排查问题。也可以参考系统提供的错误提示信息进行修改。
              <br>If you encounter compilation errors or runtime exceptions, don't panic. Carefully check the code and troubleshoot the problem step by step. You can also refer to the error prompt information provided by the system for modification.</li>
          </ul>
        </li>
        <li>
          <strong>考试时间管理 | Examination Time Management</strong>
          <ul>
            <li>严格遵守考试时间安排，提前做好准备，准时进入考试系统。考试开始后，系统将自动计时，结束时间一到，将无法继续答题。
              <br>Strictly abide by the exam time schedule. Make preparations in advance and enter the exam system on time. After the exam starts, the system will automatically time. Once the end time is up, you will not be able to continue answering questions.</li>
            <li>合理分配答题时间，对于较难的题目可以先标记，待完成其他题目后再回头解答，确保能在规定时间内完成所有题目。
              <br>Allocate your answering time reasonably. You can mark the more difficult questions first and come back to them after completing other questions to ensure that you can finish all the questions within the specified time.</li>
          </ul>
        </li>
        <li>
          <strong>诚信考试要求 | Requirements for Honest Examinations</strong>
          <ul>
            <li>严禁任何形式的作弊行为，包括但不限于查阅资料、与他人交流答案、使用辅助工具等。考试过程中，人脸识别系统会实时监控考生的行为，一旦发现违规，将严肃处理。
              <br>Any form of cheating is strictly prohibited, including but not limited to referring to materials, communicating answers with others, and using auxiliary tools. During the exam, the face recognition system will monitor the candidates' behavior in real - time. Once any violation is found, it will be dealt with seriously.</li>
            <li>尊重考试规则和监考人员的管理，如有疑问或特殊情况，及时向监考人员提出，不得擅自采取其他行动。
              <br>Respect the exam rules and the management of the invigilators. If you have any questions or special circumstances, promptly inform the invigilators and do not take other actions without permission.</li>
          </ul>
        </li>
        <li>
          <strong>异常情况处理 | Handling of Abnormal Situations</strong>
          <ul>
            <li>若在考试过程中遇到系统故障、设备问题或其他异常情况，不要关闭考试页面，应立即向监考人员报告，并提供详细的故障描述。
              <br>If you encounter system failures, device problems, or other abnormal situations during the exam, do not close the exam page. Immediately report to the invigilator and provide a detailed description of the problem.</li>
            <li>听从监考人员的指示，配合解决问题。如果需要重新登录或切换设备，按照要求进行操作，确保考试能够顺利继续。
              <br>Follow the instructions of the invigilator and cooperate to solve the problem. If you need to log in again or switch devices, operate as required to ensure that the exam can continue smoothly.</li>
          </ul>
        </li>
      </ol>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
.backgrounds {
  height: auto;
  overflow: hidden;
  background-image: url("@/assets/home/function/a.jpg");
  background-size: 100%;
  align-items: center;
  justify-content: center;
  /* 修正 filter 语法 */
  filter: 'blur(10px)';
}
</style>