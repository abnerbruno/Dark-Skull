package br.com.fatec.DarkSkull.util;

import lombok.NoArgsConstructor;

import java.util.Base64;

@NoArgsConstructor

public class ImageUtil {
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
