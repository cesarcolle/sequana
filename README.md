# Sequana

God of the Seine (france) came from deep water to help you with your garden !


## Purpose

Sequana will help you to define a watering scheduling for your garden


### Use

Edit a `garden.seq` file using the sequana grammar 

#### Example of sequana file

```
pipe_name that-name3 {
    pin 5
}
frequency daybyday {
    day monday at 18h00
    day thursday at 18h00
    day saturday at 18h00
}

device potatoes {
    pin_range [1,2]
    pin_number 1 daybyday
    model_device ARDUINOUNO
    pipe_list that-name,that-name1
}

area potager {
    potatoes
}
```

define all the _frequency_, _device_, _pipe_ and area you have then execute sequana 

Sequana will generate :

- `embeded` : all the code for yours arduino.
- `back`    : all the code for the server manager of your garden.


Then configure properly your arduino, launch the server and enjoy your garden !

// TODO : model of the arduino + sensor + network property
 