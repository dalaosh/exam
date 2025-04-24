// websocket-client.js
class WebSocketClient {
    /**
     * @typedef {(data?: any) => void} EventCallback
     * @typedef {Object} WebSocketClientOptions
     * @property {string} url - 连接地址 (必需)
     * @property {boolean} [autoReconnect=true] - 自动重连
     * @property {number} [reconnectLimit=5] - 最大重连次数
     * @property {number} [reconnectInterval=5000] - 重连间隔(ms)
     * @property {number} [heartInterval=30000] - 心跳间隔(ms)
     * @property {boolean} [debug=false] - 调试模式
     */

    /**
     * 创建 WebSocket 客户端实例
     * @param {WebSocketClientOptions} options 配置选项
     */
    constructor(options) {
        // 合并默认配置
        this._options = {
            autoReconnect: true,
            reconnectLimit: 5,
            reconnectInterval: 5000,
            heartInterval: 30000,
            debug: false,
            ...options
        };

        // 初始化核心属性
        this._ws = null;
        this._reconnectTimer = null;
        this._heartTimer = null;
        this._isActiveClose = false;
        this._reconnectCount = 0;
        this._messageQueue = [];

        // 事件监听器存储
        this._eventListeners = {
            open: [],
            message: [],
            close: [],
            error: []
        };
    }

    // 公共方法 --------------------------------------------------------

    /**
     * 建立 WebSocket 连接
     */
    connect() {
        if (this._ws) return;

        this._isActiveClose = false;
        this._ws = new WebSocket(this._options.url);

        // 绑定原生事件
        this._ws.onopen = (e) => this._handleOpen(e);
        this._ws.onmessage = (e) => this._handleMessage(e);
        this._ws.onclose = (e) => this._handleClose(e);
        this._ws.onerror = (e) => this._handleError(e);
    }

    /**
     * 主动断开连接
     */
    disconnect() {
        this._isActiveClose = true;
        this._clearTimers();
        this._ws?.close();
        this._ws = null;
    }

    /**
     * 发送消息
     * @param {any} data 要发送的数据
     */
    send(data) {
        if (this.isConnected()) {
            this._doSend(data);
        } else {
            this._options.debug && console.warn('[WebSocket] 消息已存入队列');
            this._messageQueue.push(data);
        }
    }

    /**
     * 注册事件监听
     * @param {'open'|'message'|'close'|'error'} event 事件类型
     * @param {EventCallback} callback 回调函数
     */
    on(event, callback) {
        if (this._eventListeners[event]) {
            this._eventListeners[event].push(callback);
        }
    }

    /**
     * 检查连接状态
     * @returns {boolean}
     */
    isConnected() {
        return this._ws?.readyState === WebSocket.OPEN;
    }

    // 私有方法 --------------------------------------------------------

    /** 处理连接成功 */
    _handleOpen(event) {
        this._options.debug && console.log('[WebSocket] 连接成功');
        this._reconnectCount = 0;
        this._triggerEvent('open', event);
        this._startHeartbeat();
        this._flushMessageQueue();
    }

    /** 处理接收消息 */
    _handleMessage(event) {
        try {
            const data = JSON.parse(event.data);
            this._triggerEvent('message', data);
        } catch (error) {
            this._options.debug && console.error('[WebSocket] 消息解析失败:', error);
        }
    }

    /** 处理连接关闭 */
    _handleClose(event) {
        this._triggerEvent('close', event);
        this._options.debug && console.log('[WebSocket] 连接关闭');

        if (!this._isActiveClose) {
            this._reconnect();
        }
    }

    /** 处理连接错误 */
    _handleError(error) {
        this._options.debug && console.error('[WebSocket] 连接错误:', error);
        this._triggerEvent('error', error);
        this._reconnect();
    }

    /** 触发事件回调 */
    _triggerEvent(type, data) {
        this._eventListeners[type].forEach(cb => cb(data));
    }

    /** 执行消息发送 */
    _doSend(data) {
        if (!this._ws) return;

        const payload = typeof data === 'string'
            ? data
            : JSON.stringify(data);

        this._ws.send(payload);
    }

    /** 启动心跳检测 */
    _startHeartbeat() {
        this._clearHeartbeat();
        this._heartTimer = setInterval(() => {
            this._doSend({ type: 'heartbeat', timestamp: Date.now() });
        }, this._options.heartInterval);
    }

    /** 执行自动重连 */
    _reconnect() {
        if (!this._options.autoReconnect ||
            this._reconnectCount >= this._options.reconnectLimit) return;

        this._clearTimers();
        this._reconnectCount++;

        this._options.debug &&
        console.log(`[WebSocket] 第 ${this._reconnectCount} 次重连...`);

        this._reconnectTimer = setTimeout(() => {
            this.connect();
        }, this._options.reconnectInterval);
    }

    /** 发送队列消息 */
    _flushMessageQueue() {
        while (this._messageQueue.length > 0) {
            const msg = this._messageQueue.shift();
            this._doSend(msg);
        }
    }

    /** 清理定时器 */
    _clearTimers() {
        this._clearHeartbeat();
        clearTimeout(this._reconnectTimer);
    }

    /** 清除心跳 */
    _clearHeartbeat() {
        clearInterval(this._heartTimer);
    }
}

// 导出模块
module.exports = WebSocketClient;
