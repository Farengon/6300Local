int bump = 8;
int light = 9;

void setup() {
  Serial.begin(115200);
  pinMode(bump,INPUT);
  pinMode(light,OUTPUT);
  // 其他初始化代码
}

void loop() {
  // 读取数据，例如从传感器或其他端口
  int data = digitalRead(bump);
  if(data == HIGH){
    digitalWrite(light, HIGH);
  }
  else{
    digitalWrite(light, LOW);
  }
  // 将数据发送到 ESP8266
  Serial.println(data);

  // 延迟一段时间
  delay(1000);
}
