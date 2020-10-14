package com.example.demo.entity;

public class UserDo {


        private String make;
        private int seatCount;

        public String getMake() {
                return make;
        }

        public void setMake(String make) {
                this.make = make;
        }

        public int getSeatCount() {
                return seatCount;
        }

        public void setSeatCount(int seatCount) {
                this.seatCount = seatCount;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        @Override
        public String toString() {
                return "UserDo{" +
                        "make='" + make + '\'' +
                        ", seatCount=" + seatCount +
                        ", type='" + type + '\'' +
                        '}';
        }

        private String type;

        //constructor, getters, setters etc.
}
