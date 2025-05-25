package com.proyecto.proyectofinal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.proyectofinal.model.entities.ActividadEntity;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadEntity, LocalDateTime> {
    
    // Buscar actividades por ciudad
    @Query("SELECT a FROM ActividadEntity a WHERE a.direccionActividad.ciudad.nombreCiudad = :ciudad")
    List<ActividadEntity> buscarActividadPorCiudad(@Param("ciudad") String ciudad);
    
    // Buscar actividades por interés
    @Query("SELECT a FROM ActividadEntity a JOIN a.interesesActividad i WHERE i.interes = :interes")
    List<ActividadEntity> buscarActividadesPorInteres(@Param("interes") String interes);
    
    // Buscar actividades por creador
    @Query("SELECT a FROM ActividadEntity a WHERE a.creador.cedula = :cedula")
    List<ActividadEntity> buscarActividadesPorCreador(@Param("cedula") String cedula);  

    // Buscar actividades entre fechas 
       @Query("SELECT a FROM ActividadEntity a WHERE a.fechaInicio = :inicio")
    List<ActividadEntity> buscarPorFecha(@Param("inicio") LocalDateTime inicio);

    // Buscar actividades próximas (que no han comenzado)
    @Query("SELECT a FROM ActividadEntity a WHERE a.fechaInicio > CURRENT_TIMESTAMP")
    List<ActividadEntity> buscarProximasActividades();

    //Buscar por nombre actividad
    List<ActividadEntity>findByNombreActividadContainingIgnoreCase(String nombre);
    
    //Optener las actuvidades en las que participa un usuario
    @Query("SELECT a FROM ActividadEntity a JOIN a.participantes p WHERE p.cedula = :cedula")
    List<ActividadEntity> buscarActividadesPorParticipante(@Param("cedula") String cedula);
    
   
}