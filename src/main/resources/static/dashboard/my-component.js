const { createApp } = Vue

  createApp({
  // Properties returned from data() become reactive state
  // and will be exposed on `this`.
  data() {
    return {
      count: 0
    }
  },

  // Methods are functions that mutate state and trigger updates.
  // They can be bound as event listeners in templates.
  methods: {
    increment() {
      console.log("makan yang banyak");
      axios.get('https://jsonplaceholder.typicode.com/users')
      .then( response => console.log(response.data) )
      .catch( error => console.log(error) );

      this.count++
    }
  },

  // Lifecycle hooks are called at different stages
  // of a component's lifecycle.
  // This function will be called when the component is mounted.
  mounted() {
    console.log(`The initial count is ${this.count}.`)
  }
  }).mount('#app')