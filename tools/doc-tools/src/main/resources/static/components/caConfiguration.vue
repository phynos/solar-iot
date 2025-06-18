<template>

</template>
<script>
	module.exports = {
		data: function() {
			return {
				form: {
					"organName": "",
					"organCode": "",
					"key": "",
					"signatureAlgorithm": "",
					"certificateNumber": "",
					"x509IssuerName": ""
				},
				autograph: "",
				returnData: ""
			}
		},
		props: {
			organData: {
				type: Object,
				default: () => {}
			},

		},
		watch: {

		},
		mounted() {},
		methods: {

		}
	}
</script>
<style>

</style>
