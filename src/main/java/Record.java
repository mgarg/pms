import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Record {
    @NotNull
    @Size(min = 6 ,max =6)
    final String propertyId;

    @NotNull
    @Size(min =3, max =3)
    final String chainCode;

    @NotNull
    @Size(min = 2, max = 25)
    final String cityName;

    @NotNull
    @Size(min = 2, max = 2)
    final String stateCode;

    @NotNull
    @Size(min = 2, max = 2)
    final String countryCode;

    @NotNull
    @Size(min = 2, max = 50)
    final String address;

    final Room one, two;

  @Override
  public String toString() {
    return "{" +
      propertyId + ", " +
      chainCode + "," +
      cityName + "," +
      stateCode + "," +
      countryCode + "," +
      address + "," +
      one + ","+
      two +
      '}';
  }

  public Record(String propertyId, String chainCode, String cityName, String stateCode, String countryCode, String address, Room one, Room two) {
    this.propertyId = propertyId;
    this.chainCode = chainCode;
    this.cityName = cityName;
    this.stateCode = stateCode;
    this.countryCode = countryCode;
    this.address = address;
    this.one = one;
    this.two = two;
  }

}
