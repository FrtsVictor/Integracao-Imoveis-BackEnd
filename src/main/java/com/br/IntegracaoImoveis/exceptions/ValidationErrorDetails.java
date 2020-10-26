package com.br.IntegracaoImoveis.exceptions;

import java.util.Map;

public class ValidationErrorDetails extends ErrorDetailsPattern {
	
	private String field;
	private String fieldMessage;
	
	
	public Map<String, String> errorDetails;
	
		public static final class Builder {
	        private String title;
	        private int status;
	        private String detail;
	        private long timestamp;
	        private String developerMessage;
	        private String field;
	        private String fieldMessage;
	        private Map<String, String> errorDetails;
	        
	        
	        
	        
	        public Builder(String field, String fieldMessage) {
				super();
				this.field = field;
				this.fieldMessage = fieldMessage;
			}

			public String getField() {
				return field;
			}

			public void setField(String field) {
				this.field = field;
			}

			public String getFieldMessage() {
				return fieldMessage;
			}

			public void setFieldMessage(String fieldMessage) {
				this.fieldMessage = fieldMessage;
			}

			private Builder() {
	        }

	        public static Builder newBuilder() {
	            return new Builder();
	        }

	        public Builder title(String title) {
	            this.title = title;
	            return this;
	        }

	        public Builder status(int status) {
	            this.status = status;
	            return this;
	        }

	        public Builder detail(String detail) {
	            this.detail = detail;
	            return this;
	        }

	        public Builder timestamp(long timestamp) {
	            this.timestamp = timestamp;
	            return this;
	        }

	        public Builder developerMessage(String developerMessage) {
	            this.developerMessage = developerMessage;
	            return this;
	        }
		        
	        public Builder field(String field) {
	        	this.field = field;
	        	return this;
	        }
	        
	        public Builder fieldMessage(String fieldMessage) {
	            this.fieldMessage = fieldMessage;
	            return this;
	        }
	        
	        public Builder resume(Map<String, String> resume) {
	            this.errorDetails = resume;
	            return this;
	        }
	        
	        
	        public ValidationErrorDetails build() {
	        	ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
	        	validationErrorDetails.setDeveloperMessage(developerMessage);
	        	validationErrorDetails.setTitle(title);
	        	validationErrorDetails.setDetail(detail);
	        	validationErrorDetails.setTimestamp(timestamp);
	        	validationErrorDetails.setStatus(status);
	        	validationErrorDetails.setFieldMessage(fieldMessage);
	        	validationErrorDetails.setField(field);
	        	validationErrorDetails.errorDetails = errorDetails;
	            return validationErrorDetails;
	        }
	    }

		public String getFieldMessage() {
			return fieldMessage;
		}

		public void setFieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}
	
	
}
