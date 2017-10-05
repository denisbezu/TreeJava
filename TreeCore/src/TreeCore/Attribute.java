package TreeCore;

import java.util.Comparator;

public class Attribute
{
    //region Fields
    private String _name;

    private String _value;
    //endregion

    //region Ctor

    public Attribute() {
    }

    public Attribute(String name, String value) {
        this._name = name;
        this._value = value;
    }
    //endregion

    //region Getters-Setters
    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
    //endregion

    @Override
    public int hashCode() {
        int result = _name != null ? _name.hashCode() : 0;
        result = 31 * result + (_value != null ? _value.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (_name != null ? !_name.equals(attribute._name) : attribute._name != null) return false;
        return _value != null ? _value.equals(attribute._value) : attribute._value == null;
    }

    @Override
    public String toString() {
        return _name + " : " + _value;
    }
}
