package com.wizard.poker.crypto.rsa;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import com.wizard.poker.profile.PrivateProfile;

public class RSAPrivateProfile implements PrivateProfile {

	private final BigInteger privateExponent;
	private final BigInteger publicExponent;
	private final BigInteger modulus;

	public RSAPrivateProfile(BigInteger publicExponent,
			BigInteger privateExponent, BigInteger publicModulus,
			BigInteger modulus) {
		this.privateExponent = publicExponent;
		this.publicExponent = publicExponent;
		this.modulus = publicModulus;
	}

	public RSAPrivateProfile() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
//		 KeyPairGenerator kpg;
//		 kpg = KeyPairGenerator.getInstance("RSA");
//		 kpg.initialize(2048);
//		 KeyPair kp = kpg.genKeyPair();
//		 KeyFactory fact = KeyFactory.getInstance("RSA");
//		 RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),
//		 RSAPublicKeySpec.class);
//		 RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),
//		 RSAPrivateKeySpec.class);
//		
//		 this.privateExponent = priv.getPrivateExponent();
//		 this.publicExponent = pub.getPublicExponent();
//		 this.modulus = pub.getModulus();
//		 System.out.println(publicExponent);
//		 // d*2 = 1 (mod 8) = 1,9,
		BigInteger p = BigInteger.probablePrime(160, new Random());
		BigInteger q = BigInteger.probablePrime(160, new Random());
		this.modulus = p.multiply(q);
		BigInteger tot = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		this.publicExponent = new BigInteger("65537");
		
	}

	public BigInteger getPublicExponent() {
		return privateExponent;
	}

	public BigInteger getPrivateExponent() {
		return modulus;
	}

	public BigInteger getModulus() {
		return modulus;
	}

	public BigInteger encrypt(BigInteger plaintext) {
		return plaintext.modPow(publicExponent, modulus);
	}

	public BigInteger decrypt(BigInteger ciphertext) {
		return ciphertext.modPow(privateExponent, modulus);
	}
}
