module.exports = function(grunt) {

    grunt.config.init({
        eslint: {
            all: ['src/main/resources/static/app/**/*.js'],
            options: {
                config: ".eslintrc.json"
            }
        }
    });

    grunt.loadNpmTasks('eslint-grunt');

    grunt.registerTask('default', ['eslint']);
};