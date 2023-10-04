package quynh.java.webapp.imagestore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="image_file")
public class ImageFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="uploaded_name")
	private String uploadedName;
	@Column(name="format_type")
	private String formatType;
	private byte[] data;
	private byte[] thumbnail;
	@Column(name="created_time")
	private LocalDateTime createdTime;
	@ManyToOne
	@JoinColumn(name="group_id")
	private ImageGroup imageGroup;
}
