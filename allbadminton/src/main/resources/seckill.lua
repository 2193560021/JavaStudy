--1.参数列表
local courtId = ARGV[1]

local userId = ARGV[2]

local orderId = ARGV[3]

--2.数据key
--库存key
local stockKey = 'seckill:stock:' .. courtId
--订单key
local orderKey ='seckill:order:'.. courtId

--3.脚本业务
--3.1.判断库存是否充足 get stockKey
if (tonumber(redis.call('get', stockKey)) <= 0) then
    --3.2.库存不足，返回1
    return 1
end
--3.2.判断用户是否下单 SISMEMBER orderKey userId
if (redis.call('sismember', orderKey, userId) == 1) then
    --3.3.存在，说明是重复下单，返回2
    return 2
end
--3.4.扣库存 incrby stockKey -1
redis.call('incrby', stockKey, -1)

--3.5.下单（保存用户）sadd orderKey userId
redis.call('sadd', orderKey, userId)

redis.call('xadd','stream.orders','*','userId',userId,'courtId',courtId, 'id',orderId)

return 0