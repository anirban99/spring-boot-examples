import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.3.61"
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("jacoco")
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
}

group = "com.example.api"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.flywaydb:flyway-core:6.0.7")
	// Spring Boot Actuator for Monitoring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	// Prometheus endpoint extension for Actuator
	implementation("io.micrometer:micrometer-registry-prometheus")
	// Test frameworks
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	testImplementation("com.h2database:h2:1.4.200")
//	testImplementation("com.github.javafaker:javafaker:1.0.2")
}

tasks {

	val extraTestsMatcher = "package/path/to/test/*.class"
	val extraTest = register<Test>("extraTest") {
		include(extraTestsMatcher)
		useJUnitPlatform {}
	}
	test {
		exclude(extraTestsMatcher)
		finalizedBy(jacocoTestReport)
		useJUnitPlatform {}
	}

	build {
		dependsOn(extraTest)
	}

	compileKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}

	compileTestKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}

	jacocoTestCoverageVerification {
		violationRules {
			rule { limit { minimum = BigDecimal.valueOf(0.0) } }
			rule {
				enabled = false
				element = "CLASS"
				includes = listOf("org.gradle.*")

				limit {
					counter = "LINE"
					value = "TOTALCOUNT"
					maximum = "0.3".toBigDecimal()
				}
			}
		}
	}

	jacoco {
		toolVersion = "0.8.5"
		reportsDir = file("$buildDir/customJacocoReportDir")
	}

	jacocoTestReport {
		dependsOn(test) // tests are required to run before generating the report
	}

	jacocoTestReport {
		reports {
			xml.isEnabled = true
			csv.isEnabled = false
			html.destination = file("${buildDir}/jacocoHtml")
		}
	}

	check {
		dependsOn(jacocoTestCoverageVerification)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
