package com.leadiro.starter.service.starter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leadiro.starter.service.starter.dto.Name;
import com.leadiro.starter.service.starter.util.NameBuilder;

@SpringBootTest
class NameServiceTest {

	@Autowired
	private NameBuilder name;

    @Test
    public void simple() {
        Assert.assertEquals("Leadiro User", new Name("Leadiro", "User"), name.process("Leadiro User"));
        Assert.assertEquals("User, Leadiro", new Name("Leadiro", "User"), name.process("User, Leadiro"));
        Assert.assertEquals("leadiro     User", new Name("Leadiro", "User"), name.process(" leadiro     User "));
    }

    @Test
    public void surname() {
        Assert.assertEquals("Leadiro John Del User", new Name("Leadiro", "Del User"), name.process("Leadiro John Del User"));
    }

    @Test
    public void remove() {
        Assert.assertEquals("Csar Leadiro User", new Name("Leadiro", "User"), name.process("Csar Leadiro User"));
        Assert.assertEquals("Dr Leadiro User", new Name("Leadiro", "User"), name.process("Dr Leadiro User"));
        Assert.assertEquals("D.R. Leadiro User", new Name("Leadiro", "User"), name.process("D.R. Leadiro User"));
        Assert.assertEquals("Rev. Leadiro User", new Name("Leadiro", "User"), name.process("Rev. Leadiro User"));
        Assert.assertEquals("Leadiro (John) User", new Name("Leadiro", "User"), name.process("Leadiro (John) User"));
        Assert.assertEquals("Leadiro \"Rambo\" User", new Name("Leadiro", "User"), name.process("Leadiro \"Rambo\" User"));
        Assert.assertEquals("Leadiro 'Rambo' User", new Name("Leadiro", "User"), name.process("Leadiro 'Rambo' User"));
        Assert.assertEquals("Leadiro User a.k.a The Terminator", new Name("Leadiro", "User"), name.process("Leadiro User a.k.a The Terminator"));
        Assert.assertEquals("Leadiro User M.B.A.", new Name("Leadiro", "User"), name.process("Leadiro User M.BA."));
        Assert.assertEquals("Leadiro J. R. User", new Name("Leadiro", "User"), name.process("Leadiro J. R. User"));
        Assert.assertEquals("Leadiro User, Bsc", new Name("Leadiro", "User"), name.process("Leadiro User, Bsc"));
        Assert.assertEquals("Leadiro User - Bsc", new Name("Leadiro", "User"), name.process("Leadiro User - Bsc"));
        Assert.assertEquals("Leadiro User | Bsc", new Name("Leadiro", "User"), name.process("Leadiro User | Bsc"));
        Assert.assertEquals("~~~ Leadiro User ~~~", new Name("Leadiro", "User"), name.process("~~~ Leadiro User ~~~"));
        Assert.assertEquals("Leadiro User Certified Professional", new Name("Leadiro", "User"), name.process("Leadiro User Certified Professional"));
        Assert.assertEquals("Leadiro User 99", new Name("Leadiro", "User"), name.process("Leadiro User 99"));
    }

    @Test
    public void replace() {
        Assert.assertEquals("Leadiro User II.", new Name("Leadiro", "User"), name.process("Leadiro User II."));
        Assert.assertEquals("Leadiro User Jr.", new Name("Leadiro", "User"), name.process("Leadiro User Jr."));
    }

    @Test
    public void suffix() {
        Assert.assertEquals("Leadiro User Dip Ed", new Name("Leadiro", "User"), name.process("Leadiro User Dip Ed"));
        Assert.assertEquals("Leadiro User DipEd", new Name("Leadiro", "User"), name.process("Leadiro User DipEd"));
        Assert.assertEquals("Leadiro R User MSc MPH DRes/PhD", new Name("Leadiro", "User"), name.process("Leadiro R User MSc MPH DRes/PhD"));
        Assert.assertEquals("Leadiro User Phd", new Name("Leadiro", "User"), name.process("Leadiro User Phd"));
        Assert.assertEquals("Leadiro User MacA", new Name("Leadiro", "User"), name.process("Leadiro User MacA"));
        Assert.assertEquals("Leadiro User assoc prof", new Name("Leadiro", "User"), name.process("Leadiro User assoc prof"));
    }

    @Test
    public void badNames() {
        Assert.assertNull("Harendra 8866605136", name.process("Harendra 8866605136"));
        Assert.assertNull(".. ..", name.process(".. .."));
        Assert.assertNull(" ..", name.process("*N* *O*"));
        Assert.assertEquals("~~~ Leadiro User ~~~", new Name("Leadiro", "User"), name.process("~~~ Leadiro User ~~~"));
        Assert.assertEquals("~~~ Leadiro J User ~~~", new Name("Leadiro", "User"), name.process("~~~ Leadiro J User ~~~"));
    }

    @Test
    public void capitalization() {
        Assert.assertEquals("HEMANT AHIRKAR", new Name("Hemant", "Ahirkar"), name.process("HEMANT AHIRKAR"));
        Assert.assertEquals("hemant ahirkar", new Name("Hemant", "Ahirkar"), name.process("hemant ahirkar"));
        Assert.assertEquals("Hemant deAhirkar", new Name("Hemant", "deAhirkar"), name.process("Hemant deAhirkar"));
    }

    @Test
    public void nonAlpha() {
        Assert.assertEquals("'HEMANT AHIRKAR'", new Name("Hemant", "Ahirkar"), name.process("'HEMANT AHIRKAR'"));
        Assert.assertEquals("-hemant ahirkar", new Name("Hemant", "Ahirkar"), name.process("-hemant ahirkar"));
    }

    @Test
    public void invalid() {
        Assert.assertNull(name.process("'Leadiro"));
        Assert.assertNull(name.process("'Leadir O'"));
        Assert.assertNull(name.process("'L Eadiro'"));
        Assert.assertNull(name.process("'L. E. Adiro'"));
    }

}