package com.company;

import java.util.Calendar;
import java.util.Date;



public class TimeNB {

        private long myHours;
        private long myMinutes;
        private long mySeconds;

        long getMyHours() {
            return this.myHours;
        }

        void setMyHours(final long myHours) {
            if (myHours >= 0L & myHours < 24L) {
                this.myHours = myHours;
                return;
            }
            throw new ExceptionInInitializerError("BE CAREFULL");
        }

        long getMinutes() {
            return this.myMinutes;
        }

        void setMinutes(final long minutes) {
            if (minutes >= 0L & minutes < 60L) {
                this.myMinutes = minutes;
                return;
            }
                throw new ExceptionInInitializerError("BE MUCH MORE CAREFULL");
        }

        long getMySeconds() {
            return this.mySeconds;
        }

        void setMySeconds(final long mySeconds) {
            if (mySeconds >= 0L & mySeconds < 60L) {
                this.mySeconds = mySeconds;
                return;
            }
            throw new ExceptionInInitializerError("BY SUPE CAREFULL");
        }

        TimeNB() {
            this.setMyHours(0L);
            this.setMinutes(0L);
            this.setMySeconds(0L);
        }

        TimeNB(final long hours, final long minutes, final long seconds) {
            this.setMyHours(hours);
            this.setMinutes(minutes);
            this.setMySeconds(seconds);
        }

        TimeNB(final Date date) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.setMyHours(calendar.get(11));
            this.setMinutes(calendar.get(12));
            this.setMySeconds(calendar.get(13));
        }

        @Override
        public String toString() {
            String output = "";
            if (this.myHours < 10L) {
                output += "0"+myHours;
            } else {
                output += myHours;
            }
            output += ":";
            if (this.myMinutes < 10L) {
                output += "0"+ myMinutes;
            } else {
                output += myMinutes;
            }
            output += ":";
            if (this.mySeconds < 10L) {
                output += "0"+mySeconds;
            } else {
                output += this.mySeconds;
            }
            return output;
        }

        private static TimeNB _add(final TimeNB first, final TimeNB second) {
            long seconds = 0L;
            long minutes = 0L;
            long hours = 0L;
            final long currentSeconds = first.mySeconds + second.mySeconds;
            if (currentSeconds >= 60L) {
                ++minutes;
                seconds = currentSeconds - 60L;
            } else {
                seconds = currentSeconds;
            }
            final long currentMinutes = minutes + first.myMinutes + second.myMinutes;
            if (currentMinutes >= 60L) {
                ++hours;
                minutes = currentMinutes - 60L;
            } else {
                minutes = currentMinutes;
            }
            final long currentHours = hours + first.myHours + second.myHours;
            if (currentHours >= 24L) {
                hours = currentHours - 24L;
            } else {
                hours = currentHours;
            }
            return new TimeNB(hours, minutes, seconds);
        }

        private static TimeNB _substract(final TimeNB first, final TimeNB second) {
            long hours = 0L;
            long minutes = 0L;
            long seconds = 0L;
            boolean minusSecond = false;
            if (first.myHours == 0L & first.myMinutes == 0L & first.mySeconds == 0L) {
                first.setMyHours(23L);
                first.setMinutes(59L);
                first.setMySeconds(59L);
                minusSecond = true;
            }
            final long currentHours = first.myHours - second.myHours;
            if (currentHours >= 0L) {
                hours = currentHours;
                final long currentMinutes = first.myMinutes - second.myMinutes;
                if (currentMinutes >= 0L) {
                    minutes = currentMinutes;
                } else {
                    --hours;
                    minutes = first.myMinutes + 60L - second.myMinutes;
                }
                final long currentSeconds = first.mySeconds - second.mySeconds;
                if (currentSeconds >= 0L) {
                    seconds = currentSeconds;
                } else {
                    --minutes;
                    seconds = first.mySeconds + 60L - second.mySeconds;
                }
                if (minusSecond) {
                    ++seconds;
                }
                return new TimeNB(hours, minutes, seconds);
            }
            throw new ArithmeticException("Hours out of bound exception");
        }

        public TimeNB add(final TimeNB term) {
            return _add(this, term);
        }

            public TimeNB substract(final TimeNB substracted) {
            return _substract(this, substracted);
        }

        public static TimeNB addTwoObjects(final TimeNB first, final TimeNB second) {
            return _add(first, second);
        }

                    public static TimeNB substractTwoObjects(final TimeNB first, final TimeNB second) {
            return _substract(first, second);
        }

        public static void main(String[] args) {
            System.out.println("RESULTS:");
            final TimeNB firstTime = new TimeNB(12L, 0L, 1L);
            final TimeNB secondTime = new TimeNB(12L, 0L, 1L);
            final TimeNB thirdTime = new TimeNB(new Date(10, 12, 2003, 23, 59, 59));
            System.out.println(thirdTime.toString());
            System.out.println( secondTime.add(thirdTime));
            System.out.println(new TimeNB(0L, 0L, 0L).substract(new TimeNB(0L, 0L, 1L)));
            System.out.println(addTwoObjects(new TimeNB(10L, 10L, 10L), new TimeNB(10L, 30L, 30L)));
            System.out.println(substractTwoObjects(new TimeNB(20L, 10L, 10L), new TimeNB(10L, 50L, 5L)));
        }
    }

