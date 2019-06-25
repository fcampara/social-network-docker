if (process.env.NODE_ENV === "production" || process.env.NODE_ENV === "staging") {
  console.log('teste')
  module.exports = require("./keys-env");
} else {
  console.log('teste 2')
  module.exports = require("./keys-dev");
}