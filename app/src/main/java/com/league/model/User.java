package com.league.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity
public class User implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private int userID;
    @SerializedName("avatar")
    @Embedded
    private UserImage userImage = new User.UserImage();
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String userName;
    @SerializedName("email")
    private String userEmail;
    @SerializedName("address")
    @Embedded
    private User.UserAddress userAddress = new User.UserAddress();
    @SerializedName("phone")
    private String userPhone;
    @SerializedName("website")
    private String userWebSite;
    @SerializedName("company")
    @Embedded
    private User.UserCompany userCompany = new User.UserCompany();

    public User(int userID, User.UserImage userImage, String name, String userName, String userEmail, User.UserAddress userAddress, String userPhone, String userWebSite, User.UserCompany userCompany) {
        this.userID = userID;
        this.userImage = userImage;
        this.name = name;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userWebSite = userWebSite;
        this.userCompany = userCompany;
    }

    @Ignore
    public User() {
    }

    protected User(Parcel in) {
        userID = in.readInt();
        name = in.readString();
        userName = in.readString();
        userEmail = in.readString();
        userPhone = in.readString();
        userWebSite = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserWebSite() {
        return userWebSite;
    }

    public void setUserWebSite(String userWebSite) {
        this.userWebSite = userWebSite;
    }

    public UserCompany getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(UserCompany userCompany) {
        this.userCompany = userCompany;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(name);
        dest.writeString(userName);
        dest.writeString(userEmail);
        dest.writeString(userPhone);
        dest.writeString(userWebSite);
    }

    public static class UserImage implements Parcelable{

        @Ignore
        public UserImage() {
        }

        @SerializedName("medium")
        private String userProfilePicture;

        public UserImage(String userProfilePicture) {
            this.userProfilePicture = userProfilePicture;
        }

        protected UserImage(Parcel in) {
            userProfilePicture = in.readString();
        }

        public static final Creator<UserImage> CREATOR = new Creator<UserImage>() {
            @Override
            public UserImage createFromParcel(Parcel in) {
                return new UserImage(in);
            }

            @Override
            public UserImage[] newArray(int size) {
                return new UserImage[size];
            }
        };

        public String getUserProfilePicture() {
            return userProfilePicture;
        }

        public void setUserProfilePicture(String userProfilePicture) {
            this.userProfilePicture = userProfilePicture;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(userProfilePicture);
        }
    }

    public static class UserAddress implements Parcelable{

        @Ignore
        public UserAddress() {
        }

        @SerializedName("street")
        private String streetName;
        @SerializedName("suite")
        private String suite;
        @SerializedName("city")
        private String city;
        @SerializedName("zipcode")
        private String zipCode;

        public UserAddress(String streetName, String suite, String city, String zipCode) {
            this.streetName = streetName;
            this.suite = suite;
            this.city = city;
            this.zipCode = zipCode;
        }

        protected UserAddress(Parcel in) {
            streetName = in.readString();
            suite = in.readString();
            city = in.readString();
            zipCode = in.readString();
        }

        public static final Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
            @Override
            public UserAddress createFromParcel(Parcel in) {
                return new UserAddress(in);
            }

            @Override
            public UserAddress[] newArray(int size) {
                return new UserAddress[size];
            }
        };

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(streetName);
            dest.writeString(suite);
            dest.writeString(city);
            dest.writeString(zipCode);
        }
    }

    public static class UserCompany implements Parcelable {

        @Ignore
        public UserCompany() {
        }

        @SerializedName("name")
        private String companyName;
        @SerializedName("catchPhrase")
        private String catchPhrase;
        @SerializedName("bs")
        private String bs;

        public UserCompany(String companyName, String catchPhrase, String bs) {
            this.companyName = companyName;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        protected UserCompany(Parcel in) {
            companyName = in.readString();
            catchPhrase = in.readString();
            bs = in.readString();
        }

        public static final Creator<UserCompany> CREATOR = new Creator<UserCompany>() {
            @Override
            public UserCompany createFromParcel(Parcel in) {
                return new UserCompany(in);
            }

            @Override
            public UserCompany[] newArray(int size) {
                return new UserCompany[size];
            }
        };

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        public void setBs(String bs) {
            this.bs = bs;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(companyName);
            dest.writeString(catchPhrase);
            dest.writeString(bs);
        }
    }
}


