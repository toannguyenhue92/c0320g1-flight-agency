package vn.codegym.flightagency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
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
//    @Pattern(regexp = "^[ 0-9%-A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴa-zắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ]{3,}$")
    private String namePromo;

    @ManyToOne
    @JoinColumn(name = "airline", nullable = false) //hãng bay
    private Branch airline;

    @ManyToOne
    @JoinColumn(name = "departure_place") //điểm khởi hành
    private Airport departurePlace;

    @ManyToOne
    @JoinColumn(name = "arrival_place") //điểm đến
    private Airport arrivalPlace;

    @Column(name = "discount", nullable = false) //đã chia 100
    @Min(0)
    @Max(1)
    private Double discount;

    @Column(name = "promo_date_start", nullable = false) //ngày bắt đầu chạy khuyến mãi
    private LocalDateTime promoDateStart;

    @Column(name = "promo_date_end", nullable = false) //ngày cuối cùng chạy khuyến mãi
    private LocalDateTime promoDateEnd;

    @Column(name = "flight_departure_date_start", nullable = false) //ngày bay bắt đầu được giảm giá
    private LocalDateTime flightDepartureDateStart;

    @Column(name = "flight_departure_date_end", nullable = false) //ngày bay cuối cùng được giảm giá
    private LocalDateTime flightDepartureDateEnd;

    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;
    
}
