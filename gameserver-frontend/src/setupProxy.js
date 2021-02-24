const { createProxyMiddleware } = require('http-proxy-middleware');
const proxy = require('http-proxy-middleware');
module.exports = function (app) {
    app.use('/api/', createProxyMiddleware('/api/', // replace with your endpoint
        { target: 'http://backend:7001', changeOrigin: true, pathRewrite: { '^/api/': '/' } } // replace with your target
    ));
}