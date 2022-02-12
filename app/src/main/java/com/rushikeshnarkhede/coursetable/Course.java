package com.rushikeshnarkhede.coursetable;

public class Course {
    int _id;
    String _name;
    String _duration;
    String _description;

    public Course() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_duration() {
        return _duration;
    }

    public void set_duration(String _duration) {
        this._duration = _duration;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Course(int _id, String _name, String _duration, String _description) {
        this._id=_id;
        this._name=_name;
        this._duration=_duration;
        this._description=_description;
    }

}
