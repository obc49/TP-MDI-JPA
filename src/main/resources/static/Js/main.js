var allEtudiants = [];

function findEtudiant (etudiantId) {
  return allEtudiants[getEtudiantKey(etudiantId)];
}

function getEtudiantKey (etudiantId) {
  for (var key = 0; key < allEtudiants.length; key++) {
    if (allEtudiants[key].id == etudiantId) {
      return key;
    }
  }
}

var etudiants = {
    
  getEtudiants(fn) {
    axios
      .get('/api/v1/etudiants')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  getEtudiant(id, fn) {
    axios
      .get('/api/v1/etudiants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  createEtudiant(etudiant, fn) {
    axios
      .post('/api/v1/etudiants', etudiant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  updateEtudiant(id, etudiant, fn) {
    axios
      .put('/api/v1/etudiants/' + id, etudiant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteEtudiant(id, fn) {
    axios
      .delete('/api/v1/etudiants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}



var List = Vue.extend({
  template: '#etudiant-list',
  data: function() {
    return {allEtudiants:[], searchKey: ''};
  },
  computed: {
    filteredEtudiants() {
        return this.allEtudiants.filter((etudiant) => {
            return etudiant.lastName.indexOf(this.searchKey) > -1
      	        || etudiant.firstName.indexOf(this.searchKey) > -1
      	        || etudiant.filiere.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
      etudiants.getEtudiants(r => {this.allEtudiants = r.data; allEtudiants = r.data})
      console.log(allEtudiants)
    }
});
var Etudiant = Vue.extend({
  template: '#etudiant',
  data: function () {
    return {etudiant: findEtudiant(this.$route.params.etudiant_id)};
  }
});

var EtudiantEdit = Vue.extend({
  template: '#etudiant-edit',
  data: function () {
    return {etudiant: findEtudiant(this.$route.params.etudiant_id)};
  },
  methods: {
    updateEtu: function () {
     etudiants.updateEtudiant(this.etudiant.id, this.etudiant,r => router.push('/'))
    }
  }
});

var EtudiantDelete = Vue.extend({
  template: '#etudiant-delete',
  data: function () {
    return {etudiant: findEtudiant(this.$route.params.etudiant_id)};
  },
  methods: {
    deleteEtu: function () {
      etudiants.deleteEtudiant(this.etudiant.id, r => router.push('/'))
    }
  }
});

var AddEtudiant = Vue.extend({
  template: '#add-etudiant',
  data() {
    return {
      etudiant: {lastName: '',firstName:'', filiere: ''}
    }
  },
  methods: {
    createEtu() {
      etudiants.createEtudiant(this.etudiant, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/etudiant/:etudiant_id', component: Etudiant, name: 'etudiant'},
		{path: '/add-etudiant', component: AddEtudiant},
		{path: '/etudiant/:etudiant_id/edit', component: EtudiantEdit, name: 'etudiant-edit'},
		{path: '/etudiant/:etudiant_id/delete', component: EtudiantDelete, name: 'etudiant-delete'}
	]
});

new Vue({
  router
}).$mount('#app')