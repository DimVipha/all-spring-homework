package co.istad.demomobilebanking.feature.mail;

import co.istad.demomobilebanking.feature.mail.dto.MailRequest;
import co.istad.demomobilebanking.feature.mail.dto.MailResponse;

public interface MailService {
    MailResponse send(MailRequest mailRequest);
}
