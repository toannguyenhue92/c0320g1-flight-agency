package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promo")
@Getter
@Setter
@NoArgsConstructor
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_promo", nullable = false)
    @Pattern(regexp = "^[ 0-9%-A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴa-zắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ]{3,}$")
    private String namePromo;

    @Column(name = "discount", nullable = false)
    @Min(0)
    @Max(1)
    private Long discount;

    @OneToMany(mappedBy = "branches", nullable = false) //hãng máy bay
    @JoinColumn(name = "airline_company")
    private List<Branch> airlineCompanyList;

    @OneToOne
    @JoinColumn(name = "flight_schedules") //lấy tuyến bay kết hợp gtri 2 trường departure_airport_id và arrival_airport_id
    private FlightSchedule flightSchedule;

    @Column(name = "promo_date_start", nullable = false) //ngày bắt đầu chạy khuyến mãi
    private LocalDateTime promoDateStart;

    @Column(name = "promo_date_end", nullable = false) //ngày cuối cùng chạy khuyến mãi
    private LocalDateTime promoDateEnd;

    @Column(name = "flight_departure_date_start", nullable = false) //ngày bay bắt đầu được giảm giá
    private LocalDateTime flightDepartureDateStart;

    @Column(name = "flight_departure_date_end", nullable = false) //ngày bay cuối cùng được giảm giá
    private LocalDateTime flightDepartureDateEnd;

//    @OneToOne //hạng ghế ngồi/hạng dịch vụ
//    @JoinColumn(name = "service_class")
//    private ServiceClass serviceClass;

    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;
    
}
