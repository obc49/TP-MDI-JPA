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

/* *****Enseignants************ */

var allEnseignants = [];

function findEnseignant (enseignantId) {
  return allEnseignants[getEnseignantKey(enseignantId)];
}

function getEnseignantKey (enseignantId) {
  for (var key = 0; key < allEnseignants.length; key++) {
    if (allEnseignants[key].id == enseignantId) {
      return key;
    }
  }
}

var enseignants = {
    
  getEnseignants(fn) {
    axios
      .get('/api/v1/enseignants')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  getEnseignant(id, fn) {
    axios
      .get('/api/v1/enseignants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  createEnseignant(enseignant, fn) {
    axios
      .post('/api/v1/enseignants', enseignant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  updateEnseignant(id, enseignant, fn) {
    axios
      .put('/api/v1/enseignants/' + id, enseignant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteEnseignant(id, fn) {
    axios
      .delete('/api/v1/enseignants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}



var List1 = Vue.extend({
  template: '#enseignant-list',
  data: function() {
    return {allEnseignants:[], searchKey: ''};
  },
  computed: {
    filteredEnseignants() {
        return this.allEnseignants.filter((enseignant) => {
            return enseignant.lastName.indexOf(this.searchKey) > -1
      	        || enseignant.firstName.indexOf(this.searchKey) > -1
      	        || enseignant.departement.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
      enseignants.getEnseignants(r => {this.allEnseignants = r.data; allEnseignants = r.data})
      console.log(allEnseignants)
    }
});
var Enseignant = Vue.extend({
  template: '#enseignant',
  data: function () {
    return {enseignant: findEnseignant(this.$route.params.enseignant_id)};
  }
});

var EnseignantEdit = Vue.extend({
  template: '#enseignant-edit',
  data: function () {
    return {enseignant: findEnseignant(this.$route.params.enseignant_id)};
  },
  methods: {
    updateEns: function () {
     enseignants.updateEnseignant(this.enseignant.id, this.enseignant,r => router.push('/'))
    }
  }
});

var EnseignantDelete = Vue.extend({
  template: '#enseignant-delete',
  data: function () {
    return {enseignant: findEnseignant(this.$route.params.enseignant_id)};
  },
  methods: {
    deleteEns: function () {
      enseignants.deleteEnseignant(this.enseignant.id, r => router.push('/'))
    }
  }
});

var checkedID=[];

var AddEnseignant = Vue.extend({
  template: '#add-enseignant',
  data() {
    return {
      enseignant: {lastName: '',firstName:'', departement: ''},
      allEtudiants:[],
      checkedID: []
    }
  },
  mounted() {
      etudiants.getEtudiants(r => {this.allEtudiants = r.data; allEtudiants = r.data})
    },
 methods: {

    createEns() {
      enseignants.createEnseignant(this.enseignant, r => router.push('/'))
    },
    
    ajoutID(){
        this.checkedID = findEtudiant(this.$route.params.etudiant_id)
        console.log(this.checkedID)
    }
  }
});

var router = new VueRouter({
	routes: [
        {path: '/', component: List},
        {path: '/enseignants', component: List1},
		{path: '/etudiant/:etudiant_id', component: Etudiant, name: 'etudiant'},
		{path: '/add-etudiant', component: AddEtudiant},
		{path: '/etudiant/:etudiant_id/edit', component: EtudiantEdit, name: 'etudiant-edit'},
        {path: '/etudiant/:etudiant_id/delete', component: EtudiantDelete, name: 'etudiant-delete'},
        {path: '/enseignant/:enseignant_id', component: Enseignant, name: 'enseignant'},
		{path: '/add-enseignant', component: AddEnseignant},
		{path: '/enseignant/:enseignant_id/edit', component: EnseignantEdit, name: 'enseignant-edit'},
		{path: '/enseignant/:enseignant_id/delete', component: EnseignantDelete, name: 'enseignant-delete'}
	]
});

new Vue({
  router
}).$mount('#app')