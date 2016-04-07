pin = 1
    print("Pin ")
    print(pin)
    print("start, engaged, off ")  
    
    pwm.setup(pin,20,50)
    pwm.start(pin)

    tmr.delay(2*1000000)
    
    pwm.stop(pin)
    print("-----")
