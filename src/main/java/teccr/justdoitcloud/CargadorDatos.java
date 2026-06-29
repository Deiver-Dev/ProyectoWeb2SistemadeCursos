package teccr.justdoitcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.RolUsuario;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.servicios.CategoriaServicio;
import teccr.justdoitcloud.servicios.CursoServicio;
import teccr.justdoitcloud.servicios.UsuarioServicio;

@Component
public class CargadorDatos implements CommandLineRunner {

    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private CategoriaServicio categoriaServicio;
    @Autowired private CursoServicio cursoServicio;

    @Override
    public void run(String... args) {

        Usuario profesor = usuarioServicio.registrar(
                "Carlos Profesor", "profesor@tec.cr", "123456", RolUsuario.PROFESOR);

        usuarioServicio.registrar(
                "Ana Estudiante", "estudiante@tec.cr", "123456", RolUsuario.ESTUDIANTE);

        usuarioServicio.registrar(
                "Luis Estudiante", "luis@tec.cr", "123456", RolUsuario.ESTUDIANTE);

        Categoria programacion = categoriaServicio.crear("Programación", "Cursos de desarrollo de software");
        Categoria cloud = categoriaServicio.crear("Cloud & DevOps", "Cursos de nube, contenedores y despliegue");
        Categoria ia = categoriaServicio.crear("Inteligencia Artificial", "Cursos de IA, datos y automatización");
        Categoria seguridad = categoriaServicio.crear("Ciberseguridad", "Cursos de seguridad informática");
        Categoria martech = categoriaServicio.crear("MarTech", "Cursos de tecnología aplicada al marketing digital");

        cursoServicio.crear("Programación Web con Spring", "Desarrollo de aplicaciones web usando Spring Boot y Thymeleaf.", 4, profesor, programacion);
        cursoServicio.crear("Java Avanzado", "Patrones de diseño, buenas prácticas y arquitectura en Java.", 3, profesor, programacion);
        cursoServicio.crear("Bases de Datos SQL", "Modelado relacional, consultas SQL y optimización básica.", 3, profesor, programacion);
        cursoServicio.crear("Ingeniería de Software", "Análisis, diseño y construcción de sistemas mantenibles.", 4, profesor, programacion);
        cursoServicio.crear("Desarrollo Frontend con React", "Construcción de interfaces modernas con componentes reutilizables.", 3, profesor, programacion);
        cursoServicio.crear("APIs REST con Spring Boot", "Diseño y exposición de servicios REST para aplicaciones web.", 3, profesor, programacion);

        cursoServicio.crear("AWS Cloud Practitioner", "Fundamentos de servicios cloud, infraestructura y buenas prácticas en AWS.", 3, profesor, cloud);
        cursoServicio.crear("Microsoft Azure Fundamentals", "Introducción a servicios cloud, recursos y despliegues en Azure.", 3, profesor, cloud);
        cursoServicio.crear("Docker Essentials", "Uso de contenedores para empaquetar y ejecutar aplicaciones.", 2, profesor, cloud);
        cursoServicio.crear("Kubernetes para Desarrolladores", "Orquestación de contenedores y despliegues escalables.", 4, profesor, cloud);
        cursoServicio.crear("DevOps Fundamentals", "Integración continua, despliegue continuo y cultura DevOps.", 3, profesor, cloud);

        cursoServicio.crear("Introducción a la Inteligencia Artificial", "Conceptos base de IA y aplicaciones en la industria.", 3, profesor, ia);
        cursoServicio.crear("Machine Learning", "Modelos supervisados, no supervisados y evaluación de resultados.", 4, profesor, ia);
        cursoServicio.crear("Ciencia de Datos", "Análisis de datos, visualización e interpretación de información.", 4, profesor, ia);
        cursoServicio.crear("Prompt Engineering", "Diseño de instrucciones efectivas para herramientas de IA generativa.", 2, profesor, ia);

        cursoServicio.crear("Seguridad Informática", "Principios de protección de sistemas, redes y datos.", 3, profesor, seguridad);
        cursoServicio.crear("Ethical Hacking", "Técnicas básicas de análisis de vulnerabilidades y pruebas controladas.", 4, profesor, seguridad);
        cursoServicio.crear("Seguridad en Aplicaciones Web", "Buenas prácticas para proteger aplicaciones web modernas.", 3, profesor, seguridad);

        cursoServicio.crear("Marketing Technology Fundamentals", "Fundamentos del ecosistema MarTech y plataformas digitales.", 3, profesor, martech);
        cursoServicio.crear("CRM Administration with HubSpot", "Administración de contactos, pipelines y automatizaciones en HubSpot.", 3, profesor, martech);
        cursoServicio.crear("Salesforce Marketing Cloud", "Email marketing, journeys y automatización con Salesforce Marketing Cloud.", 4, profesor, martech);
        cursoServicio.crear("Adobe Experience Platform Fundamentals", "Introducción a datos de clientes, audiencias y experiencias digitales.", 4, profesor, martech);
        cursoServicio.crear("Customer Data Platforms CDP", "Gestión de datos de clientes y segmentación avanzada.", 4, profesor, martech);
        cursoServicio.crear("Marketing Automation", "Diseño de campañas automatizadas y flujos de comunicación.", 3, profesor, martech);
        cursoServicio.crear("Digital Analytics with GA4", "Medición de comportamiento digital con Google Analytics 4.", 3, profesor, martech);
        cursoServicio.crear("Google Tag Manager", "Implementación y gestión de etiquetas para medición digital.", 2, profesor, martech);
        cursoServicio.crear("Customer Journey Orchestration", "Diseño de experiencias omnicanal centradas en el cliente.", 4, profesor, martech);
        cursoServicio.crear("Personalization & Customer Experience", "Estrategias de personalización basadas en datos del cliente.", 3, profesor, martech);
        cursoServicio.crear("Email Marketing Strategy", "Diseño de campañas de email efectivas y medibles.", 2, profesor, martech);
        cursoServicio.crear("MarTech Solution Architecture", "Diseño de soluciones integradas entre CRM, CDP, analytics y automatización.", 4, profesor, martech);

        System.out.println("==> Datos de prueba cargados correctamente");
        System.out.println("==> Profesor: profesor@tec.cr / 123456");
        System.out.println("==> Estudiante: estudiante@tec.cr / 123456");
    }
}