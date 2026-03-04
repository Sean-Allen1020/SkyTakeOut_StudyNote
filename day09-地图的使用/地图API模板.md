### 调用地图API --> 实现地理编码(坐标)获取，和测算距离及开车用时

#### 执行步骤

1. 通过地图API提供的 uri，以及所需参数(均在API文档上)，拼接 url
   - 创建 map集合，存储所需参数
   - 创建 `URIBuilder对象`，遍历 map 调用`addParameter()`，添加参数
   - 最后调用`build().toString()`完成 url 拼接
   - 参考以下方法`getAddressUrl`
  
2. 执行 `httpClient` 代码，向第三方发送请求，并获取响应体

3. 将响应体转换成`JSONObject`对象，并根据响应体结构(API文档里有)，进一步获取具体所需要的数据
   - 当前案例中，如果要获取位置坐标，则是继续 `getJSONObject("location")`，来获取对象
   - 然后再获取对象里的参数，参数是什么类型，就用 `get参数类型()`的方法获取
   - 参考以下方法`getLatLng`，以及`第44行`开始的代码


```java
    public class MapDemo {

        private static final String AK = "zbtCM05liFSINnTVz3aJBn27wkn6yLhT";
        private static final String ownerAddress = "四川省自贡市自流井区丹桂街道英祥丽景花园A2栋";
        private static final String userAddress = "四川省自贡市自流井区春华路2号";

        public static String mapTest() throws URISyntaxException, IOException, ParseException {
            // 地理编码uri
            String addressUri = "https://api.map.baidu.com/geocoding/v3";
            // 拼接地址uril
            String url = getAddressUrl(addressUri, ownerAddress, AK);
            // 执行 httpClient
            String addressBody = doGet(url);
            String ownerLl = getLatLng(addressBody);

            /***************************获取顾客地址坐标*****************************/
            String urlUser = getAddressUrl(addressUri, userAddress, AK);
            String bodyUser = doGet(urlUser);
            String userLl = getLatLng(bodyUser);

            /****************************规划路线，计算距离*******************************************/

            // 路线规划uri
            String rootUri = "https://api.map.baidu.com/directionlite/v1/driving";
            String rootUrl = getRootUrl(rootUri, ownerLl, userLl, AK);
            String rootBody = doGet(rootUrl);

            JSONObject jsonRoot = new JSONObject(rootBody);
            JSONArray route = jsonRoot.getJSONObject("result").getJSONArray("routes");
            JSONObject routeObj = (JSONObject) route.get(0);
            String distance = routeObj.get("distance").toString();
            String duration = String.valueOf((((Integer)routeObj.get("duration"))/60));

            return "距离：" + distance + ", 用时：" + duration;
        }

        /**
         * 执行 httpCLient get 请求
         * @param url
         * @return
         * @throws IOException
         * @throws ParseException
         */
        public static String doGet(String url) throws IOException, ParseException {

            // 发送httpClient请求，获取地址坐标
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse res = httpClient.execute(new HttpGet(url));
            // 获取json格式的body
            String body = EntityUtils.toString(res.getEntity());
            // 释放资源
            res.close();
            httpClient.close();

            return body;
        }

        /**
         * 地址uri拼接
         * @param uri
         * @param address
         * @param ak
         * @return
         * @throws URISyntaxException
         */
        public static String getAddressUrl(String uri, String address, String ak) throws URISyntaxException {
            // 拼接地址uri
            Map<String, String> map = new HashMap<>();
            map.put("address", address);
            map.put("output", "json");
            map.put("ak", ak);

            URIBuilder builder = new URIBuilder(uri);
            map.forEach(builder::addParameter);
            String addressUrl = builder.build().toString();

            return addressUrl;
        }

        /**
         * 路线url拼接
         * @param uri
         * @param origin
         * @param destination
         * @param ak
         * @return
         * @throws URISyntaxException
         */
        public static String getRootUrl(String uri, String origin, String destination, String ak) throws URISyntaxException {

            Map<String, String> map = new HashMap<>();
            map.put("origin", origin);
            map.put("destination", destination);
            map.put("ak", ak);

            URIBuilder builder = new URIBuilder(uri);
            map.forEach(builder::addParameter);
            String rootUrl = builder.build().toString();

            return rootUrl;
        }

        public static String getLatLng(String body){
            
            // 通过 JSONObject，获取需要的json数据
            // 创建JSON对象
            JSONObject json = new JSONObject(body);
            // 获取location对象
            JSONObject location = json.getJSONObject("result").getJSONObject("location");
            // 获取location对象中，坐标属性(属性是什么类型就get什么类型)
            BigDecimal lat = location.getBigDecimal("lat");
            BigDecimal lng = location.getBigDecimal("lng");
            String latLng = lat + "," + lng;
            
            return latLng;
        }
    }
```