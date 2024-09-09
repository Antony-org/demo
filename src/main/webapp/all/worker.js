
self.addEventListener('message', function(event) {
    if (event.data.type === 'start') {
        var eventSource = new EventSource(event.data.url);

        eventSource.onmessage = function (e) {
            self.postMessage({ type: 'message', data: e.data });
        };

        eventSource.onerror = function (e) {
            self.postMessage({ type: 'error', data: e });
        };
    }
});
