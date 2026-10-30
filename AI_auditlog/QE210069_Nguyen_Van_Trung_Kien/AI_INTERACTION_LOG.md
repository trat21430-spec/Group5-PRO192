# 🤖 NHẬT KÝ SỬ DỤNG AI (AI AUDIT LOG)

- **Họ và tên:** Nguyễn Văn Trung Kiên
- **MSSV:** QE210069
- **Vai trò:** Trưởng nhóm (Leader) - Nhóm 5 (SE21B - PRO192 FA26)
- **Đề tài:** P02 – Mini Mart

---

## 📋 Danh sách các lần sử dụng AI hỗ trợ

### Lần 1: Rà soát Class Diagram, tư vấn cây thư mục và phân chia module

- **Ngày thực hiện:** 28/10/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Kiểm tra sơ đồ lớp (Class Diagram):** Đưa đề bài và hình ảnh sơ đồ lớp để AI kiểm tra tính hợp lý theo chuẩn OOP và yêu cầu đề bài. Nhờ AI phát hiện các lỗi lệch đề quan trọng (chuyển kiểu tiền tệ từ `double` sang `long`, sửa kiểu trả về của `validateSale` thành `void`, sửa typo trong `FileStorageManager`).
  2. **Gợi ý cây thư mục dự án:** Tư vấn cấu trúc package (`model`, `service`, `storage`, `exception`) và thư mục `data/` dùng đường dẫn tương đối.
  3. **Phân chia module độc lập cho 5 thành viên:** Lên phương án tách file cho 5 bạn trong nhóm để mỗi người làm một tập file riêng biệt, đảm bảo không bị xung đột (conflict) khi merge nhánh trên Git.
  4. **Tạo `.gitignore`:** Tư vấn và tạo file `.gitignore` để loại bỏ các file rác sinh ra trong quá trình build (`target/`, `.class`, cấu hình IDE NetBeans / IntelliJ / VS Code).

- **Phần sinh viên tự kiểm tra và điều chỉnh:**
  - Tự chỉnh sửa lại sơ đồ lớp trên Draw.io theo các điểm góp ý và hoàn thiện bản diagram chuẩn 10/10.
  - Yêu cầu chỉ tạo khung thư mục rỗng với file `.gitkeep`, không sinh code tự động để các thành viên tự lập trình theo đúng nhiệm vụ được giao.
  - Tự phân công nhiệm vụ cụ thể cho từng bạn trong nhóm dựa trên độ khó.

---

### Lần 2: Kiểm tra, test nghiệm thu code của thành viên nhóm (Võ Thanh Huy & Nguyễn Kiến Quốc)

- **Ngày thực hiện:** 30/10/2026
- **Công cụ AI sử dụng:** Antigravity (Google DeepMind)
- **Mục đích sử dụng:**
  1. **Kiểm tra và review code thành viên:** Sử dụng AI để rà soát, kiểm tra logic các đoạn code do **Võ Thanh Huy** và **Nguyễn Kiến Quốc** đã thực hiện và push lên Git (bao gồm các lớp `Cart.java`, `CartItem.java` và các lớp `Product`).
  2. **Thiết kế bộ test tự động (Unit Test):** Nhờ AI thiết kế và tạo test runner tự động `CartTestRunner.java` để kiểm thử toàn diện các quy tắc nghiệp vụ khắt khe của giỏ hàng (chuẩn hóa mã hoa, giới hạn 1–1000/dòng, gộp số lượng khi trùng mã, không vượt 1000, tối đa 20 mã/giỏ, đóng băng khi đã xác nhận `confirmed`, tính bất biến của unmodifiable map).
  3. **Thực thi kiểm thử thực tế:** Chạy trực tiếp 23 ca kiểm thử biên trên JDK 8, đối chiếu kết quả mong đợi với kết quả thực tế để nghiệm thu chất lượng code của Huy và Quốc.
- **Trạng thái (Status):** **COMPLETE** (Toàn bộ 23/23 ca kiểm thử đều ĐẠT 100%, nghiệm thu hoàn tất phần việc của Võ Thanh Huy và Nguyễn Kiến Quốc).
- **Phần sinh viên quan sát, phản biện và tự điều chỉnh (Human-in-the-loop):**
  - **Tình huống phản biện:** Trong quá trình rà soát code các lớp sản phẩm (`RegularProduct`, `ExpiringProduct`), AI có đưa ra ví dụ gợi ý gọi `super.validateSale(saleDate, quantity)` trong các lớp con để kế thừa phần kiểm tra của lớp cha.
  - **Phản biện của sinh viên:** Tôi đã phát hiện và phản biện lại ngay rằng phương thức `validateSale` được khai báo trong lớp cha `Product` là một **phương thức trừu tượng (`abstract`)**, tức là hoàn toàn không có phần thân (`body`). Trong Java, việc gọi `super` đến một phương thức `abstract` sẽ gây lỗi biên dịch (*"abstract method cannot be accessed directly"*). Do đó, tôi không làm theo gợi ý này của AI mà giữ nguyên việc cài đặt logic kiểm tra độc lập phù hợp với từng lớp con (`RegularProduct` kiểm tra tồn kho, `ExpiringProduct` kiểm tra cả hạn sử dụng).
- **Kế hoạch tiếp theo:** Làm cơ sở vững chắc để tiếp tục phối hợp, kiểm thử và tích hợp các phần nghiệp vụ tiếp theo do **Lương Võ Gia Phúc** và **Trương Thanh Trà** thực hiện (các lớp Service, File Storage và Menu).

---

*(Nhật ký sẽ tiếp tục được cập nhật khi có các lần tương tác AI tiếp theo)*
