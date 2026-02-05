```java
    @Component
    public class RedisCacheUtil {

        @Autowired
        private RedisTemplate redisTemplate;

        /**
         * 从redis中获取菜品列表
         *
         * @param key
         * @return
         */
            // 通过泛型，让编译器在编译期间，确定集合的类型   注意：Object 不是泛型！！！
        public <T> List<T> getDishVOListFromRedis(String key) {

            //检查redis数据库是否有数据
            List<T> list = (List<T>) redisTemplate.opsForValue().get(key);
            //如果有数据，直接返回数据
            if (list != null) {
                return list;
            }

            return null;
        }

        /**
         * 将菜品列表存入redis
         *
         * @param key
         * @param list
         */
            // 通过泛型，让编译器在编译期间，确定集合的类型   注意：Object 不是泛型！！！
        public <T> void setDishVOListToRedis(String key, List<T> list) {

            //集合转换为json
            Object dishVOListJson = JSON.toJSON(list);
            redisTemplate.opsForValue().set(key, dishVOListJson, 20, java.util.concurrent.TimeUnit.MINUTES);
        }

        /**
         * 批量清理菜品缓存
         */
        public void clearCacheFromRedis() {

            Set keys = redisTemplate.keys("category_*");
            redisTemplate.delete(keys);
        }

        /**
         * 清理某个分类下的菜品缓存
         *
         * @param key
         */
        public void clearCacheFromRedis(String key) {

            redisTemplate.delete(key);
        }
    }
```
**注意**：通过泛型，让编译器在编译期间，确定集合的类型   注意：Object 不是泛型！！！