#include <ESP8266WiFi.h>
const char* ssid = "6300t2";
const char* password = "63006300";
void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("WiFi connected");
}
WiFiClient client;
const char* serverIP = "192.168.43.1";  // TCP服务器的IP地址
int serverPort = 8080;                  // TCP服务器的端口


void loop() {
  if (client.connect(serverIP, serverPort)) {
    Serial.println("Connected to server");

  } else {
    Serial.println("Connection to server failed");
  }

  if (Serial.available()) {
    String sensorData = Serial.readStringUntil('\n');
    client.print(sensorData);
  } else {
    client.print(".");
  }

  client.stop();  // 关闭连接
  delay(500);    // 等待一段时间后重试
}
