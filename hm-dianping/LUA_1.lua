
local key = KEYS[1]
--当前线程标识
local threadId = ARGV[1]

--
-- 加锁
local id = redis.call('get', key)

--比较

if(id == threadId) then
    return redis.call('del', key)
end
return 0