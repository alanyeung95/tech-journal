## no-store & no-cache
https://blog.techbridge.cc/2017/06/17/cache-introduction/

假設 A 網站是使用Cache-Control: no-store，B 網站是使用Cache-Control: no-cache。

當每一次重新造訪同樣一個頁面的時候，無論 A 網站有沒有更新，A 網站都會傳來「整份新的檔案」，假設index.html有 100 kb 好了，造訪了十次，累積的流量就是 1000kb。

B 網站的話，我們假設前九次網站都沒有更新，一直到第十次才更新。所以前九次 Server 只會回傳 Status code 304，這個封包大小我們姑且算作 1kb 好了。第十次因為有新的檔案，會是 100kb。那十次加起來的流量就是 9 + 100 = 109 kb

可以發現 A 跟 B 達成的效果一樣，那就是「只要網站更新，使用者就能立即看到結果」，但是 B 的流量遠低於 A，因為有善用快取策略。只要每一次 Request 都先確認網站有沒有更新即可，不用每一次都抓完整的檔案下來。

這就是no-store跟no-cache的差異，永遠不用快取跟永遠檢查快取。

## X-Frame-Options

