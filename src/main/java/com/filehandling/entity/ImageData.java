package com.filehandling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "ImageData")
@Data
@Builder
public class ImageData {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String type;
	  
	    @Lob
	    @Column(name = "imagedata", columnDefinition = "MEDIUMBLOB")
	    private byte[] imageData;
	 
		private ImageData(Builder builder) {
	        this.id = builder.id;
	        this.name = builder.name;
	        this.type = builder.type;
	        this.imageData = builder.imageData;
	    }

	    public static Builder builder() {
	        return new Builder();
	    }

	    public static class Builder {
	        public String type;
			private Long id;
	        private String name;
	        private byte[] imageData;

	        public Builder id(Long id) {
	            this.id = id;
	            return this;
	        }

	        public Builder name(String name) {
	            this.name = name;
	            return this;
	        }
	        
	        public Builder type(String type) {
	            this.type = type;
	            return this;
	        }

	        public Builder imageData(byte[] imageData) {
	            this.imageData = imageData;
	            return this;
	        }

	        public ImageData build() {
	            return new ImageData(this);
	        }
	    }

		public ImageData(Long id, String name, String type, byte[] imageData) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
			this.imageData = imageData;
		}
	      
	    
}
