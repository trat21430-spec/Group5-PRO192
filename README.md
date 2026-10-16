<div align="center">

# 🛒 P02 – Mini Mart

**Ứng dụng Java console quản lý hàng hóa, tồn kho và lập hóa đơn bán hàng**

![Java](https://img.shields.io/badge/Java-JDK%208-orange?logo=openjdk&logoColor=white)
![IDE](https://img.shields.io/badge/IDE-NetBeans%2017-1B6AC6?logo=apachenetbeanside&logoColor=white)
![Type](https://img.shields.io/badge/Type-Console%20App-lightgrey)
![Course](https://img.shields.io/badge/Course-PRO192-blue)
![Class](https://img.shields.io/badge/Class-SE21B-green)
![Semester](https://img.shields.io/badge/Semester-FA26-purple)

`FPTU-FA26-PRO192-SE21B-Group5`

</div>

---

## 📑 Mục lục

1. [Thành viên nhóm](#-thành-viên-nhóm)
2. [Giới thiệu bài toán](#-giới-thiệu-bài-toán)
3. [Chức năng bắt buộc](#-chức-năng-bắt-buộc)
4. [Quy tắc dữ liệu](#-quy-tắc-dữ-liệu)
5. [Quy tắc hóa đơn và giá](#-quy-tắc-hóa-đơn-và-giá)
6. [Xác nhận, hủy và lưu dữ liệu](#-xác-nhận-hủy-và-lưu-dữ-liệu)
7. [Thiết kế OOP](#-thiết-kế-oop)
8. [Dữ liệu mẫu](#-dữ-liệu-mẫu)
9. [Ca nghiệm thu](#-ca-nghiệm-thu)
10. [Yêu cầu kỹ thuật](#-yêu-cầu-kỹ-thuật)
11. [Sản phẩm nộp](#-sản-phẩm-nộp)

---

## 👥 Thành viên nhóm

| STT | Họ và tên | MSSV | Vai trò |
|:---:|-----------|------|---------|
| 1 | **Nguyễn Văn Trung Kiên** | QE210069 | 👑 Trưởng nhóm (Leader) |
| 2 | Trương Thanh Trà | QE200100 | Thành viên |
| 3 | Nguyễn Kiến Quốc | QE210181 | Thành viên |
| 4 | Lương Võ Gia Phúc | QE210140 | Thành viên |
| 5 | Võ Thanh Huy | QE210119 | Thành viên |

> Lớp **SE21B** · Môn **PRO192** · Nhóm **5 sinh viên** · Thời gian **14 ngày** kể từ ngày giao đề.

---

## 📖 Giới thiệu bài toán

Một cửa hàng nhỏ cần ứng dụng console để **quản lý hàng hóa, nhập thêm tồn kho và lập hóa đơn bán hàng**. Cửa hàng có **hàng thường** và **hàng có hạn sử dụng**; nhân viên phải tránh bán hàng hết hạn hoặc bán vượt số lượng còn lại.

Luồng nghiệp vụ cốt lõi:

```text
Tìm hàng → Lập giỏ nhiều dòng → Kiểm tra khả năng bán → Tính giảm giá
        → Xác nhận hóa đơn → Cập nhật kho → Lưu dữ liệu
```

🎯 **Trọng tâm:** thiết kế OOP đúng và nghiệp vụ tính đúng.

🚫 **Ngoài phạm vi:** GUI, database, thanh toán trực tuyến, đăng nhập, thuế VAT, trả/đổi hàng, bảo hành.

---

## ✅ Chức năng bắt buộc

| # | Chức năng | Nội dung chính |
|:-:|-----------|----------------|
| 1 | **Danh mục sản phẩm** | Thêm, xem danh sách, tìm theo mã/tên, sửa tên và giá. Chọn loại thường / có hạn sử dụng khi thêm. Mã và loại không đổi sau khi tạo; không bắt buộc xóa. |
| 2 | **Tồn kho** | Xem số lượng còn lại; nhập thêm bằng số nguyên dương. **Không** cho sửa trực tiếp tồn kho bằng số tùy ý. |
| 3 | **Giỏ hàng** | Thêm sản phẩm + số lượng, sửa số lượng, xóa dòng; hiển thị thành tiền, tổng trước giảm, tiền giảm, số phải trả. Tối đa **20 mã khác nhau** mỗi giỏ. |
| 4 | **Bán hàng** | Xem trước hóa đơn, xác nhận hoặc hủy. Chỉ xác nhận khi mọi dòng hợp lệ; mã hóa đơn duy nhất; trừ kho **đúng một lần**. |
| 5 | **Tra cứu** | Liệt kê hóa đơn đã bán; xem chi tiết theo mã (ngày bán, từng dòng, ba số tiền tổng hợp). |
| 6 | **Lưu / đọc file** | Phục hồi danh mục, tồn kho, hóa đơn sau khi đóng/mở lại; phân biệt được hai loại sản phẩm sau khi đọc. |

---

## 📐 Quy tắc dữ liệu

| Dữ liệu | Quy định |
|---------|----------|
| Mã sản phẩm | 3–10 ký tự chữ/số, duy nhất; chuẩn hóa về **chữ hoa** trước khi kiểm tra trùng |
| Tên sản phẩm | Sau khi bỏ khoảng trắng hai đầu, dài 1–80 ký tự |
| Đơn giá | Số nguyên 1 – 10.000.000 đồng; **không dùng `double` cho tiền** |
| Tồn kho | Số nguyên 0 – 100.000; nhập thêm không được vượt giới hạn |
| Số lượng mỗi dòng | Số nguyên 1 – 1.000 |
| Hàng thường | Chỉ kiểm tra tồn kho và dữ liệu cơ bản khi bán |
| Hàng có hạn sử dụng | Thêm ngày hết hạn `yyyy-MM-dd`; kiểm tra cả hạn dùng khi bán |

- Mỗi mã hàng có hạn sử dụng chỉ có **một hạn dùng duy nhất** (không quản lý nhiều lô trong một mã; hàng nhập có hạn khác phải tạo mã mới).
- **Ngày bán** nhập một lần khi lập giỏ và giữ nguyên trong hóa đơn.
- Bán được nếu **ngày bán ≤ ngày hết hạn**. Ví dụ: hết hạn `2026-10-16` → bán được ngày 16, từ chối ngày 17.

---

## 💰 Quy tắc hóa đơn và giá

- Thêm cùng mã nhiều lần → **gộp số lượng** thành một dòng; kiểm tra giới hạn và tồn kho trên tổng đã gộp.
- Thành tiền dòng = đơn giá lúc xác nhận × số lượng.
- Tổng trước giảm **S** = tổng thành tiền các dòng (tính bằng `long`).
- **Chính sách giảm giá duy nhất:** S ≥ 200.000 đồng → giảm **5% toàn hóa đơn**; ngược lại giảm 0. Không cộng dồn ưu đãi khác.

```text
Khi S ≥ 200.000:   D = (S * 5 + 50) / 100     (làm tròn đồng gần nhất, 0,5 làm tròn lên)
Số phải trả:       T = S - D
```

- Giá trong giỏ lấy từ danh mục hiện tại; nếu giá đổi trước khi xác nhận → tính lại và cho xem trước tổng mới.
- Hóa đơn đã xác nhận lưu **mã hàng, tên hàng, đơn giá lúc bán, số lượng, thành tiền** từng dòng cùng S, D, T. Sửa tên/giá danh mục về sau **không làm đổi hóa đơn cũ**.

---

## 🔒 Xác nhận, hủy và lưu dữ liệu

- Giỏ rỗng không được xác nhận.
- Trước khi đổi kho, kiểm tra **toàn bộ** các dòng: mã tồn tại, số lượng, hạn dùng, tổng số lượng từng mã so với tồn kho.
- Một dòng sai → **từ chối cả giỏ**: không trừ kho, không tạo hóa đơn, báo rõ dòng và lý do.
- Hủy giỏ chưa xác nhận không đổi kho và không tạo hóa đơn.
- Một giỏ chỉ chuyển *đang soạn* → *đã xác nhận* **một lần**; xác nhận lại không tạo hóa đơn mới, không trừ kho lần hai.
- Khi lưu, dữ liệu chứa đồng thời hóa đơn mới và tồn kho mới.
- Ghi file thất bại → báo rõ **chưa lưu**, giữ dữ liệu đang có, cho thử lại; không báo thành công giả.
- Mã hóa đơn không trùng sau khi khởi động lại.
- Lần chạy đầu chưa có file = dữ liệu rỗng; file hỏng → báo lỗi rõ ràng, **không tự ghi đè**.

---

## 🧱 Thiết kế OOP

```text
Product (abstract)
 ├── RegularProduct          hàng thường
 └── ExpiringProduct         hàng có hạn sử dụng (bổ sung kiểm tra ngày)

Invoice  ◆── InvoiceLine     composition; dòng lưu giá trị chụp lúc bán
SalesService                 kiểm tra toàn giỏ, phối hợp xác nhận
Menu (console)               chỉ nhập liệu, gọi nghiệp vụ, hiển thị
```

> Tên lớp chỉ là gợi ý; được đổi nếu hành vi tương đương.

- `Product` chứa thông tin chung và khai báo `validateSale(LocalDate saleDate, int quantity)`.
- Danh mục giữ collection kiểu `Product`; khi xác nhận giỏ, gọi `validateSale` qua tham chiếu `Product` để runtime chọn hành vi → **đa hình**, không dùng chuỗi `if` kiểm tra tên loại.
- `InvoiceLine` giữ **giá trị chụp lúc bán**, không tham chiếu giá hiện tại của sản phẩm.
- Mọi thuộc tính `private`; dùng tường minh `this.` và `super.`.
- Tách rõ: dữ liệu/đối tượng – nghiệp vụ – đọc/ghi file – menu.

Nhóm phải chỉ ra được **vị trí gọi đa hình thực tế** và giải thích **vì sao hóa đơn cũ không phụ thuộc giá danh mục hiện tại**.

---

## 📦 Dữ liệu mẫu

| Mã | Tên | Loại | Giá (đồng) | Tồn | Hạn sử dụng |
|----|-----|------|-----------:|----:|-------------|
| P01 | Gạo túi nhỏ | Thường | 25.000 | 20 | — |
| P02 | Sữa hộp | Có hạn sử dụng | 15.000 | 10 | 2026-10-16 |
| P03 | Bánh quy | Có hạn sử dụng | 20.000 | 8 | 2026-10-15 |
| P04 | Bình nước | Thường | 120.000 | 5 | — |
| P05 | Hộp quà kiểm tra làm tròn | Thường | 200.010 | 3 | — |

---

## 🧪 Ca nghiệm thu

Mỗi ca bắt đầu từ dữ liệu gốc, trừ ca ghi rõ tiếp nối. Bảng dưới là **kết quả mong đợi**; nhóm phải chạy và ghi lại **kết quả thực tế**.

| Ca | Thao tác | Kết quả mong đợi | Thực tế | Đạt? |
|----|----------|------------------|:-------:|:----:|
| MM01 | Ngày 2026-10-16, mua P01×2 và P02×3 | S=95.000, D=0, T=95.000; P01 còn 18, P02 còn 7; 1 hóa đơn 2 dòng | ⬜ | ⬜ |
| MM02 | Mua P01×8 | S=200.000, D=10.000, T=190.000; P01 còn 12 (đúng ngưỡng) | ⬜ | ⬜ |
| MM03 | Mua P04×2 | S=240.000, D=12.000, T=228.000; P04 còn 3 | ⬜ | ⬜ |
| MM04 | Mua P05×1 | S=200.010, D=10.001, T=190.009; P05 còn 2 (làm tròn) | ⬜ | ⬜ |
| MM05 | Thêm P01×12 rồi P01×9 | Tổng 21 > tồn 20 → từ chối, không lập hóa đơn, P01 vẫn 20 | ⬜ | ⬜ |
| MM06 | Ngày 2026-10-16, giỏ có P01×1 và P03×1 | P03 hết hạn → từ chối cả giỏ; P01=20, P03=8 | ⬜ | ⬜ |
| MM07 | P02×1 ngày 2026-10-16; chạy lại dữ liệu gốc ngày 2026-10-17 | Ngày 16 bán được, trả 15.000, tồn 9; ngày 17 từ chối, tồn vẫn 10 | ⬜ | ⬜ |
| MM08 | Lập giỏ P01×8, xem trước rồi hủy | Không có hóa đơn mới, P01 vẫn 20 | ⬜ | ⬜ |
| MM09 | Xác nhận giỏ P04×2, rồi xác nhận lại chính giỏ đó | Chỉ 1 hóa đơn 228.000; P04 vẫn 3 | ⬜ | ⬜ |
| MM10 | Tiếp MM09: sửa giá P04 → 130.000, lưu, khởi động lại | P04 tồn 3, giá 130.000; hóa đơn cũ vẫn đơn giá 120.000, phải trả 228.000; hóa đơn kế tiếp có mã khác | ⬜ | ⬜ |
| MM11 | Giá 0, số lượng bán 0, mã trùng `p01`, nhập thêm P01 số lượng 99.981 | Mỗi thao tác bị từ chối đúng lý do; dữ liệu gốc không đổi | ⬜ | ⬜ |

**Yêu cầu tối thiểu:** ≥ **12 ca kiểm thử thực tế**, gồm các ca trên và ca bổ sung phủ: đầu vào sai, mã trùng/không tồn tại, dữ liệu rỗng, thao tác thất bại không đổi trạng thái, đa hình, lưu → thoát → đọc lại. Mỗi ca ghi: đầu vào, kết quả mong đợi, kết quả thực tế, đạt/chưa đạt.

---

## ⚙️ Yêu cầu kỹ thuật

- Java console, **JDK 1.8**, **NetBeans 17**; chỉ dùng thư viện chuẩn Java SE (`ArrayList`, `HashMap`, `Comparator`, `java.time`, I/O…).
- Menu lặp sau mỗi thao tác, có lựa chọn thoát; kiểm tra dữ liệu rỗng, sai kiểu, sai miền, trùng/không tồn tại và báo lỗi rõ.
- Thao tác bị từ chối **không được làm thay đổi một phần** dữ liệu.
- Lưu bằng file văn bản hoặc Java Serialization (ghi rõ định dạng); dùng **đường dẫn tương đối**.
- Lần chạy đầu có thể rỗng hoặc nạp dữ liệu mẫu; đã có dữ liệu thì **không** nạp mẫu đè.
- Tiền tính bằng đồng, tổng bằng `long`.

**Mở rộng (tùy chọn, chỉ làm khi phần bắt buộc đã ổn định):** báo cáo doanh thu theo ngày, cảnh báo hàng sắp hết hạn trong 7 ngày, danh sách hàng tồn thấp.

---

## 📤 Sản phẩm nộp

- [ ] Repository GitHub `FPTU-FA26-PRO192-SE21B-GroupXX` (mời collaborator `khoand96`; mỗi người commit từ tài khoản cá nhân)
- [ ] Project NetBeans 17 / JDK 8 Clean and Build được, kèm file **JAR** và hướng dẫn chạy
- [ ] README đầy đủ (build/chạy, dữ liệu mẫu, vị trí file lưu, chức năng hoàn thành, giới hạn còn lại)
- [ ] Báo cáo PDF ngắn (phạm vi, sơ đồ lớp khớp code, vị trí đa hình, phân công theo MSSV, bảng kiểm thử, lỗi còn lại)
- [ ] `docs/ai_logs/AI_INTERACTION_LOG.md` ghi các lần dùng AI quan trọng

**Bảo vệ:** demo một luồng thành công, một trường hợp bị từ chối đúng quy tắc, một tình huống đa hình, dữ liệu sau khi mở lại; từng thành viên giải thích phần mình, một ca kiểm thử và thực hiện một thay đổi nhỏ theo yêu cầu giảng viên.
