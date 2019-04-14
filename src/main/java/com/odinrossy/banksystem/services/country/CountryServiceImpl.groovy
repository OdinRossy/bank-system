package com.odinrossy.banksystem.services.country

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.repositories.country.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository

    @Override
    List<Country> findAll() {
        return countryRepository.findAll() as List<Country>
    }

    @Override
    Country findByIso3code(String iso3code) throws ResourceNotFoundException {

        Country country = countryRepository.findByIso3code(iso3code)

        if (!country)
            throw new ResourceNotFoundException("Country not found. ISO3Code: ${iso3code}.")

        return country
    }

    @Override
    Country save(Country country) throws ResourceAlreadyExistsException, ResourceNotValidException {
        try {
            Country countryFromDB = findByIso3code(country.iso3code)
            throw new ResourceAlreadyExistsException("Country already exists. ${countryFromDB}.")

        } catch(ResourceNotFoundException ignored) {
            if (!country)
                throw new ResourceNotValidException("Country not valid. ${country}.")

            return findByIso3code(countryRepository.save(country).iso3code)
        }
    }

    @Override
    Country update(String iso3code, Country country) throws ResourceNotFoundException, ResourceNotValidException {
        country.iso3code = iso3code

        findByIso3code(country.iso3code)

        if (!country)
            throw new ResourceNotValidException("Country not valid. ${country}.")

        return findByIso3code(countryRepository.save(country).iso3code)
    }

    @Override
    def delete(String iso3code) throws ResourceNotFoundException {
        countryRepository.delete(findByIso3code(iso3code))
    }
}
