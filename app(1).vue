<template>
  <div id="app">
    <router-view />

    <floating-character
      :target-route="''"
      :size="60"
      :drag-threshold="5"
      @click="openMedicalAdvisor"
    />

    <medical-advisor :visible.sync="medicalAdvisorVisible" />
  </div>
</template>

<script>
import FloatingCharacter from '@/components/ai/FloatingCharacter.vue'
import MedicalAdvisor from '@/components/ai/MedicalAdvisor.vue'

export default {
  components: {
    FloatingCharacter,
    MedicalAdvisor
  },
  data() {
    return {
      medicalAdvisorVisible: false
    }
  },
  methods: {
    openMedicalAdvisor() {
      this.medicalAdvisorVisible = true
    }
  }
}
</script>