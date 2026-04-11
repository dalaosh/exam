<template>
  <div id="app">
    <router-view/>
    <floating-character
      v-if="showAiAssistant"
      :target-route="''"
      :size="60"
      :drag-threshold="5"
      @click="openMedicalAdvisor"
    />
    <medical-advisor
      v-if="showAiAssistant"
      :visible.sync="medicalAdvisorVisible"
    />
  </div>
</template>

<script>
import FloatingCharacter from '@/components/FloatingCharacter.vue'
import MedicalAdvisor from '@/components/MedicalAdvisor.vue'

export default {
  name: 'App',
  components: {
    FloatingCharacter,
    MedicalAdvisor
  },
  data() {
    return {
      medicalAdvisorVisible: false
    }
  },
  computed: {
    showAiAssistant() {
      const homeRoutes = ['/', '/admin/home', '/teacher/home', '/student/home', '/caInstitution/home']
      return homeRoutes.includes(this.$route.path)
    }
  },
  methods: {
    openMedicalAdvisor() {
      this.medicalAdvisorVisible = true
    }
  }
}
</script>
