module.exports = {
    // 修改的配置
    publicPath: '/',
    devServer: {
        proxy: {
            '/': {
                target: 'http://localhost:8088',
                changeOrigin: true,
                ws: false,
                pathRewrite: {
                    '^/': ''
                }
            }
        }
    }
};