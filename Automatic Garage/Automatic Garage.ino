  #include <Servo.h>
  long distance2, duration2;
  int benda1; //benda 1 as Infra red detektor
  int trigPin2=4; // trigPin2 at pin 4 as Ultrasonic Sensor HC-SR04
  int echoPin2=5;// echoPin2 at pin 5 as Ultrasonic Sensor HC-SR04
  int IR = 7; // IR as Infra red detektor
  int led=13; // led at pin 13 as LED Lamp
  Servo servo;
  const int buttonPin = 8; //const value buttonPin at pin 8 as a button
  int buttonState = 0; 
  
  void setup() {

    pinMode(trigPin2, OUTPUT);
    pinMode(echoPin2,INPUT);
    pinMode(IR, INPUT);
    digitalWrite(trigPin2, LOW);
    pinMode(led, OUTPUT);
    servo.attach(10);
    Serial.begin(9600);
    pinMode(buttonPin, INPUT);
  }
  
  void loop() {
    sensor1();
    sensor2();
    buttonState = digitalRead(buttonPin);
    if (benda1 == LOW) {
        servo.write(50);
        Serial.print("MOBIL MAU MASUK ");
        delay(4000);
    }else{
      if (buttonState == HIGH) {
    servo.write(50);
        Serial.print("MOBIL AKAN KELUAR ");
        delay(4000);
  } else {
    servo.write(0);
        Serial.print("TIDAK ADA MOBIL ");
  }
        }
        
    if (distance2 < 5){
        digitalWrite(led,HIGH);
        Serial.print(" LAMPU NYALA ");
  }else{
        digitalWrite(led,LOW);
        Serial.print(" LAMPU MATI ");
  }
Serial.print(" Dengan jarak = ");
Serial.print(distance2);
Serial.println(" cm");
  }

  
  void sensor1(){
    benda1 = digitalRead(IR);
  }
  
  void sensor2(){
    digitalWrite(trigPin2, LOW);
    delayMicroseconds(500);
    digitalWrite(trigPin2,HIGH);
    delayMicroseconds(500);
    duration2 = pulseIn(echoPin2, HIGH);
    distance2 = duration2*0.034/2;
    
  }
