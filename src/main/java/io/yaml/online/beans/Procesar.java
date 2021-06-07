package io.yaml.online.beans;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@RegisterForReflection
@EqualsAndHashCode
@Entity
@Table(name = "procesars")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "items",
  "idField",
  "nameField",
  "customFields",
  "progressField"
})
public class Procesar {

  @Id
  @Column(name = "id")
  @JsonProperty("id")
  @Getter @Setter
  private Long id;

  @Column(name = "items")
  @JsonProperty("items")
  @Getter @Setter
  private String items;

  @Column(name = "idField")
  @JsonProperty("idField")
  @Getter @Setter
  private String idField;

  @Column(name = "nameField")
  @JsonProperty("nameField")
  @Getter @Setter
  private String nameField;

  @OneToMany(mappedBy = "procesar", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty("customFields")
  @Getter @Setter
  private List<CustomField> customFields;

  @OneToOne(mappedBy = "procesar", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty("progressField")
  @Getter @Setter
  private ProgressField progressField;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="onlineSessionId", nullable = false)
  @JsonbTransient
  @Getter @Setter
  private OnlineSession onlineSession;
}
