srv:listen(80, function(conn)
    conn:on("receive", function(conn, payload)
        local pin = 1
        local firstLine = string.match(payload, ".+ HTTP")
        pwm.setup(pin, 20, 100)
        if string.find(firstLine, "/start") ~= nil then
            print("Starting")
            pwm.start(pin)
            conn:send("OK")
        elseif string.find(firstLine, "/stop") ~= nil then
            print("Stopping")
            pwm.stop(pin)
            conn:send("OK")
        elseif string.find(firstLine, "/speed") ~= nil then
            local speed = string.match(firstLine, "%d+")
            print(string.format("Speed %i", speed))
            pwm.setduty(pin, speed)
            conn:send("OK")
        else
            print(firstLine)
            conn:send("<h1>Doing nothing</h1>")
        end
        print("-----------")
    end)
end)
