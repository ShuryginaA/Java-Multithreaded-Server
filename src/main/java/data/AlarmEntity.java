package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Table(name = "alarms")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmEntity {

    @Id
    @Column(name = "time")
    private String time;

    @Column(name = "event")
    private String event;
}
