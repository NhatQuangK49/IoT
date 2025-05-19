const int Gas_pin = A1;       // Chân analog kết nối với MQ-2
const float RL = 10.0;        // Điện trở tải (kΩ)
const float Vc = 5.0;         // Điện áp cung cấp (V)
float Ro = 10.0;              // Giá trị R0, cần hiệu chuẩn trong không khí sạch

// Hệ số đường thẳng từ đồ thị log-log cho khí LPG
const float m = -0.473;       // Hệ số góc
const float b = 1.413;        // Hệ số chặn
#include <Arduino.h>
void setup() {
  Serial.begin(9600);
  delay(2000); // Chờ cảm biến ổn định

}

void loop() {
  Serial.println(analogRead(Gas_pin));
  float Vrl = analogRead(Gas_pin) * Vc / 1023.0;
  float Rs = (Vc - Vrl) * RL / Vrl;
  float ratio = Rs / Ro;
  float ppm = pow(10, (log10(ratio) - b) / m);

  Serial.print("Nồng độ khí LPG (ppm): ");
  Serial.println(ppm);
  delay(1000);
}


