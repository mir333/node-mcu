if wifi.sta.getip() == nil then
    wifi.setmode(wifi.STATION)
    wifi.sta.config("ligasnet","****")
    print(wifi.sta.getip())
end

if srv == nil then
    srv=net.createServer(net.TCP) 
    dofile("server.lua")
end
